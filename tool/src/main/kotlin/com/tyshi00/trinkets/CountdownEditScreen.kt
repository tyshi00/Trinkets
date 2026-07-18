package com.tyshi00.trinkets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import com.thelightphone.sdk.LightScreen
import com.thelightphone.sdk.LightViewModel
import com.thelightphone.sdk.SealedLightActivity
import com.thelightphone.sdk.ui.LightBarButton
import com.thelightphone.sdk.ui.LightBottomBar
import com.thelightphone.sdk.ui.LightIcons
import com.thelightphone.sdk.ui.LightScrollView
import com.thelightphone.sdk.ui.LightText
import com.thelightphone.sdk.ui.LightTextField
import com.thelightphone.sdk.ui.LightTextVariant
import com.thelightphone.sdk.ui.LightTheme
import com.thelightphone.sdk.ui.LightThemeController
import com.thelightphone.sdk.ui.LightThemeTokens
import com.thelightphone.sdk.ui.LightTopBar
import com.thelightphone.sdk.ui.LightTopBarCenter
import com.thelightphone.sdk.ui.gridUnitsAsDp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val COUNTDOWN_NAME_MAX_LENGTH = 60
private const val COUNTDOWN_NOTES_MAX_LENGTH = 250

data class CountdownEditState(
    val name: String = "",
    val date: String = todayStr(),
    val notes: String = "",
    val dateFormat: DateFormat = DateFormat.MDY,
    val isEditing: Boolean = false,
    val canSave: Boolean = false,
)

class CountdownEditViewModel(
    private val repo: TrinketsRepository,
    private val existingId: Long?,
) : LightViewModel<Boolean>() {
    private val _state = MutableStateFlow(
        CountdownEditState(isEditing = existingId != null),
    )
    val state: StateFlow<CountdownEditState> = _state.asStateFlow()

    fun loadDateFormat() {
        viewModelScope.launch(Dispatchers.IO) {
            val format = repo.getDateFormat()
            _state.value = _state.value.copy(dateFormat = format)
        }
    }

    fun setName(name: String) {
        _state.value = _state.value.copy(name = name, canSave = name.isNotBlank())
    }

    fun setDate(date: String) {
        _state.value = _state.value.copy(date = date)
    }

    fun setNotes(notes: String) {
        _state.value = _state.value.copy(notes = notes)
    }

    fun save(onSaved: () -> Unit) {
        val s = _state.value
        val trimmedName = s.name.trim()
        if (trimmedName.isBlank()) return
        viewModelScope.launch(Dispatchers.IO) {
            val success = if (existingId != null) {
                repo.updateCountdown(existingId, trimmedName, s.date, s.notes)
                true
            } else {
                repo.addCountdown(trimmedName, s.date, s.notes)
            }
            if (success) {
                onSaved()
            }
        }
    }

    fun delete(onDeleted: () -> Unit) {
        val id = existingId ?: return
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteCountdown(id)
            onDeleted()
        }
    }
}

/**
 * Add or edit a countdown. Pass [existing] to pre-fill the form and switch
 * into edit mode (adds a Delete action to the bottom bar); leave it null to
 * create a new countdown.
 */
class CountdownEditScreen(
    sealedActivity: SealedLightActivity,
    private val repo: TrinketsRepository,
    private val existing: CountdownDisplayItem? = null,
) : LightScreen<Boolean, CountdownEditViewModel>(sealedActivity) {

    override val viewModelClass: Class<CountdownEditViewModel>
        get() = CountdownEditViewModel::class.java

    override fun createViewModel(): CountdownEditViewModel {
        val vm = CountdownEditViewModel(repo, existingId = existing?.id)
        if (existing != null) {
            vm.setName(existing.name)
            vm.setDate(existing.date)
            vm.setNotes(existing.notes.orEmpty())
        }
        vm.loadDateFormat()
        return vm
    }

    @Composable
    override fun Content() {
        val themeColors by LightThemeController.colors.collectAsState()
        val state by viewModel.state.collectAsState()

        LightTheme(colors = themeColors) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightThemeTokens.colors.background),
            ) {
                LightTopBar(
                    leftButton = LightBarButton.LightIcon(
                        icon = LightIcons.BACK,
                        onClick = { goBack(false) },
                    ),
                    center = LightTopBarCenter.Text(if (state.isEditing) "Edit Countdown" else "New Countdown"),
                    modifier = Modifier.padding(bottom = 1f.gridUnitsAsDp()),
                )

                LightScrollView(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 1f.gridUnitsAsDp()),
                ) {
                    LightTextField(
                        label = "Name",
                        value = state.name,
                        placeholder = "e.g. Trip to Lisbon",
                        onClick = {
                            navigateTo(
                                screenFactory = {
                                    TextEditorScreen(
                                        it,
                                        title = "Name",
                                        initialValue = state.name,
                                        maxLength = COUNTDOWN_NAME_MAX_LENGTH,
                                    )
                                },
                                resultCallback = { result -> if (result != null) viewModel.setName(result) },
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Spacer(modifier = Modifier.height(1.5f.gridUnitsAsDp()))

                    LightTextField(
                        label = "Date",
                        value = dateLabel(state.date, state.dateFormat),
                        placeholder = "",
                        onClick = {
                            navigateTo(
                                screenFactory = { CountdownDatePickerScreen(it, state.date) },
                                resultCallback = { result -> if (result != null) viewModel.setDate(result) },
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Spacer(modifier = Modifier.height(1.5f.gridUnitsAsDp()))

                    LightTextField(
                        label = "Notes (optional)",
                        value = state.notes.ifBlank { "Not set" },
                        placeholder = "",
                        onClick = {
                            navigateTo(
                                screenFactory = {
                                    TextEditorScreen(
                                        it,
                                        title = "Notes",
                                        initialValue = state.notes,
                                        maxLength = COUNTDOWN_NOTES_MAX_LENGTH,
                                    )
                                },
                                resultCallback = { result -> if (result != null) viewModel.setNotes(result) },
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 4,
                    )
                }

                LightBottomBar(
                    items = if (state.isEditing) {
                        listOf(
                            LightBarButton.LightIcon(
                                icon = LightIcons.CLOSE,
                                contentDescription = "Cancel",
                                onClick = { goBack(false) },
                            ),
                            LightBarButton.LightIcon(
                                icon = LightIcons.SAVE,
                                contentDescription = "Save",
                                onClick = { viewModel.save(onSaved = { goBack(true) }) },
                            ).takeIf { state.canSave },
                            LightBarButton.LightIcon(
                                icon = LightIcons.TRASH,
                                contentDescription = "Delete",
                                onClick = {
                                    navigateTo(
                                        screenFactory = {
                                            ConfirmResetScreen(
                                                it,
                                                "Delete \"${state.name}\"?",
                                                title = "Confirm deletion",
                                                confirmLabel = "DELETE",
                                            )
                                        },
                                        resultCallback = { confirmed ->
                                            if (confirmed == true) viewModel.delete(onDeleted = { goBack(true) })
                                        },
                                    )
                                },
                            ),
                        )
                    } else {
                        listOf(
                            LightBarButton.LightIcon(
                                icon = LightIcons.CLOSE,
                                contentDescription = "Cancel",
                                onClick = { goBack(false) },
                            ),
                            LightBarButton.LightIcon(
                                icon = LightIcons.SAVE,
                                contentDescription = "Save",
                                onClick = { viewModel.save(onSaved = { goBack(true) }) },
                            ).takeIf { state.canSave },
                        )
                    },
                )
            }
        }
    }
}

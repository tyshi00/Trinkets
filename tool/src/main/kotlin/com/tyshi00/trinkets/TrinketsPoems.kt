package com.tyshi00.trinkets

// Two kinds of poem live here:
//
// 1. Original writing for this app. Usually author = null; a few carry a
//    byline (the ones credited to "MVt", a pseudonym).
// 2. Public-domain poems, reproduced in full with the author set. Rules:
//    - US public domain only (author long dead, or first published before
//      1929). Contemporary poems are never reproduced in full; a short
//      attributed line can go in Excerpts instead.
//    - No devotional or overtly religious poems: nothing that is a hymn,
//      prayer, or whose subject is God, Christ, or faith. Incidental
//      references ("Eden", "whatever gods may be") are fine.
//    - Authors with well-documented records of racism, antisemitism,
//      homophobia, transphobia, or similar bigotry are excluded.
//
// Poem text is left-aligned on screen (see TextContentScreen), so line
// breaks and indentation in the raw strings below are shown as written.
// See docs/CONTENT.md before adding more.
object TrinketsPoems {
    val ALL: List<Poem> = listOf(

        // --- Original poems ---

        Poem(1, "Practice", """
            I am not good at this yet,
            not the waking early, not the patience,
            not the letting go of what I can't hold.
            But a beginner is not a failure.
            A beginner is only early in the story.
        """.trimIndent()),
        Poem(2, "Borrowed Courage", """
            Some days my courage is entirely my own.
            Other days I'm just borrowing it
            from someone who believed in me
            before I believed in myself.
        """.trimIndent()),
        Poem(3, "Small Mercies", """
            A warm cup held in cold hands.
            A stranger who let you merge.
            A song that arrived right on time.
            Notice these. They are the quiet proof
            that the world is not only hard.
        """.trimIndent()),
        Poem(4, "The Long Practice of Small Joys", """
            I am collecting small joys now,
            the smell of rain, a good song,
            an easy laugh,
            proof that the good stuff doesn't have to be big to count.
        """.trimIndent()),
        Poem(5, "Kept Warm", """
            Someone, somewhere, is thinking of you
            more gently than you think of yourself.
            You may never know who.
            Let that be reason enough
            to be gentle with someone else today.
        """.trimIndent()),
        Poem(6, "Wordless Comfort", """
            Some people fill a room with noise.
            Others fill it just by being present,
            a steady, wordless comfort
            that somehow says everything.
        """.trimIndent()),
        Poem(7, "Between Stations", """
            I am not who I was at the last stop,
            not yet who I'll be at the next one.
            Right now I am only motion,
            only the space between two names for myself,
            and that, it turns out, is allowed.
        """.trimIndent()),
        Poem(8, "The Empty Chair at Dinner", """
            We keep setting the table for five,
            even now, even after,
            because love doesn't always know
            how to make room for less.
        """.trimIndent()),
        Poem(9, "Held Breath", """
            There is a moment right before the good news,
            right before the hard news,
            when nothing has happened yet.
            Learn to be at peace there too.
        """.trimIndent()),
        Poem(10, "Repair", """
            Every mended thing carries the seam
            where it was broken, and that's not shame,
            that's proof it was worth fixing.
            Wear your seams. They're not scars.
            They're the record of your staying.
        """.trimIndent()),

        // --- Original poems (bylined) ---

        Poem(11, "Cruelty", """
            Humanity regresses
            I drown in my spirals
            The rich continue to plot
            I continue to rot.

            The dark, evil minds
            all seem unbound, flourishing
            I continue to rot.
        """.trimIndent(), "MVt"),
        Poem(12, "To all the oppressed", """
            Can you hear the chanting,
            the echoing,
            the bass deep in your chest,
            feel your insides
            shaking, trembling,
            that fire of justice
            crying, howling

            STAND UP
            FIGHT BACK
            STOMP DOWN
            TEAR DOWN
            PUSH BACK
            LIBERATION
                 DRAWS
                   NEAR!
        """.trimIndent(), "MVt"),
        Poem(13, "Did you find hope?", """
            Under the torn blanket
            Behind the stove
            Near the fallen, mossy tree
            In your deepest thoughts
            Did you find hope?

            In your loved ones caress
            In their loving embrace
            looking up at the stars
            the cosmic enlightenment
            Did you find what you were looking for?
        """.trimIndent(), "MVt"),
        Poem(14, "Longing", """
            There are times I find myself
            Yearning
            For the quiet of the trees
            The clarity of the brook
            The destruction of a wave
            The gentleness of a breeze

            I want a contradictory balance
            To hold both calm and fury at once
            To desire the explosion of nothingness
            I want to understand the universe in me
            To understand the lives beyond mine

            Do the wise
            Draw lines
            With opposing forces?
        """.trimIndent(), "MVt"),

        // --- Public-domain poems ---

        Poem(15, "Nothing Gold Can Stay", """
            Nature's first green is gold,
            Her hardest hue to hold.
            Her early leaf's a flower;
            But only so an hour.
            Then leaf subsides to leaf.
            So Eden sank to grief,
            So dawn goes down to day.
            Nothing gold can stay.
        """.trimIndent(), "Robert Frost"),
        Poem(16, "Dreams", """
            Hold fast to dreams
            For if dreams die
            Life is a broken-winged bird
            That cannot fly.
            Hold fast to dreams
            For when dreams go
            Life is a barren field
            Frozen with snow.
        """.trimIndent(), "Langston Hughes"),
        Poem(17, "Mother to Son", """
            Well, son, I'll tell you:
            Life for me ain't been no crystal stair.
            It's had tacks in it,
            And splinters,
            And boards torn up,
            And places with no carpet on the floor —
            Bare.
            But all the time
            I'se been a-climbin' on,
            And reachin' landin's,
            And turnin' corners,
            And sometimes goin' in the dark
            Where there ain't been no light.
            So boy, don't you turn back.
            Don't you set down on the steps
            'Cause you finds it's kinder hard.
            Don't you fall now —
            For I'se still goin', honey,
            I'se still climbin',
            And life for me ain't been no crystal stair.
        """.trimIndent(), "Langston Hughes"),
        Poem(18, "I, Too", """
            I, too, sing America.

            I am the darker brother.
            They send me to eat in the kitchen
            When company comes,
            But I laugh,
            And eat well,
            And grow strong.

            Tomorrow,
            I'll be at the table
            When company comes.
            Nobody'll dare
            Say to me,
            "Eat in the kitchen,"
            Then.

            Besides,
            They'll see how beautiful I am
            And be ashamed —

            I, too, am America.
        """.trimIndent(), "Langston Hughes"),
        Poem(19, "The Negro Speaks of Rivers", """
            I've known rivers:
            I've known rivers ancient as the world and older than the flow of human blood in human veins.

            My soul has grown deep like the rivers.

            I bathed in the Euphrates when dawns were young.
            I built my hut near the Congo and it lulled me to sleep.
            I looked upon the Nile and raised the pyramids above it.
            I heard the singing of the Mississippi when Abe Lincoln went down to New Orleans, and I've seen its muddy bosom turn all golden in the sunset.

            I've known rivers:
            Ancient, dusky rivers.

            My soul has grown deep like the rivers.
        """.trimIndent(), "Langston Hughes"),
        Poem(20, "If We Must Die", """
            If we must die—let it not be like hogs
            Hunted and penned in an inglorious spot,
            While round us bark the mad and hungry dogs,
            Making their mock at our accursed lot.
            If we must die—oh, let us nobly die,
            So that our precious blood may not be shed
            In vain; then even the monsters we defy
            Shall be constrained to honor us though dead!
            Oh, Kinsmen! we must meet the common foe;
            Though far outnumbered, let us show us brave,
            And for their thousand blows deal one death-blow!
            What though before us lies the open grave?
            Like men we'll face the murderous, cowardly pack,
            Pressed to the wall, dying, but fighting back!
        """.trimIndent(), "Claude McKay"),
        Poem(21, "The Tropics in New York", """
            Bananas ripe and green, and ginger-root,
            Cocoa in pods and alligator pears,
            And tangerines and mangoes and grape fruit,
            Fit for the highest prize at parish fairs,

            Set in the window, bringing memories
            Of fruit-trees laden by low-singing rills,
            And dewy dawns, and mystical blue skies
            In benediction over nun-like hills.

            My eyes grew dim, and I could no more gaze;
            A wave of longing through my body swept,
            And, hungry for the old, familiar ways,
            I turned aside and bowed my head and wept.
        """.trimIndent(), "Claude McKay"),
        Poem(22, "From the Dark Tower", """
            We shall not always plant while others reap
            The golden increment of bursting fruit,
            Nor always countenance, abject and mute,
            That lesser men should hold their brothers cheap;
            Not everlastingly while others sleep
            Shall we beguile their limbs with mellow flute,
            Not always bend to some more subtle brute;
            We were not made eternally to weep.

            The night whose sable breast relieves the stark,
            White stars is no less lovely being dark,
            And there are buds that cannot bloom at all
            In light, but crumple, piteous, and fall.

            So in the dark we hide the heart that bleeds,
            And wait, and tend our agonizing seeds.
        """.trimIndent(), "Countee Cullen"),
        Poem(23, "Your World", """
            Your world is as big as you make it.
            I know, for I used to abide
            In the narrowest nest in a corner,
            My wings pressing close to my side.

            But I sighted the distant horizon
            Where the skyline encircled the sea
            And I throbbed with a burning desire
            To travel this immensity.

            I battered the cordons around me
            And cradled my wings on the breeze,
            Then soared to the uttermost reaches
            With rapture, with power, with ease!
        """.trimIndent(), "Georgia Douglas Johnson"),
        Poem(24, "Bury Me in a Free Land", """
            Make me a grave where'er you will,
            In a lowly plain, or a lofty hill;
            Make it among earth's humblest graves,
            But not in a land where men are slaves.

            I could not rest if around my grave
            I heard the steps of a trembling slave;
            His shadow above my silent tomb
            Would make it a place of fearful gloom.

            I could not rest if I heard the tread
            Of a coffle gang to the shambles led,
            And the mother's shriek of wild despair
            Rise like a curse on the trembling air.

            I could not sleep if I saw the lash
            Drinking her blood at each fearful gash,
            And I saw her babes torn from her breast,
            Like trembling doves from their parent nest.

            I'd shudder and start if I heard the bay
            Of bloodhounds seizing their human prey,
            And I heard the captive plead in vain
            As they bound afresh his galling chain.

            If I saw young girls from their mother's arms
            Bartered and sold for their youthful charms,
            My eye would flash with a mournful flame,
            My death-paled cheek grow red with shame.

            I would sleep, dear friends, where bloated might
            Can rob no man of his dearest right;
            My rest shall be calm in any grave
            Where none can call his brother a slave.

            I ask no monument, proud and high,
            To arrest the gaze of the passers-by;
            All that my yearning spirit craves,
            Is bury me not in a land of slaves.
        """.trimIndent(), "Frances E. W. Harper"),
        Poem(25, "We Wear the Mask", """
            We wear the mask that grins and lies,
            It hides our cheeks and shades our eyes, —
            This debt we pay to human guile;
            With torn and bleeding hearts we smile,
            And mouth with myriad subtleties.

            Why should the world be over-wise,
            In counting all our tears and sighs?
            Nay, let them only see us, while
            We wear the mask.

            We smile, but, O great Christ, our cries
            To thee from tortured souls arise.
            We sing, but oh the clay is vile
            Beneath our feet, and long the mile;
            But let the world dream otherwise,
            We wear the mask!
        """.trimIndent(), "Paul Laurence Dunbar"),
        Poem(26, "Life", """
            A crust of bread and a corner to sleep in,
            A minute to smile and an hour to weep in,
            A pint of joy to a peck of trouble,
            And never a laugh but the moans come double;
                And that is life!

            A crust and a corner that love makes precious,
            With a smile to warm and the tears to refresh us;
            And joy seems sweeter when cares come after,
            And a moan is the finest of foils for laughter;
                And that is life!
        """.trimIndent(), "Paul Laurence Dunbar"),
        Poem(27, "“Hope” is the thing with feathers", """
            "Hope" is the thing with feathers
            That perches in the soul,
            And sings the tune without the words,
            And never stops at all,

            And sweetest in the gale is heard;
            And sore must be the storm
            That could abash the little bird
            That kept so many warm.

            I've heard it in the chillest land,
            And on the strangest sea;
            Yet, never, in extremity,
            It asked a crumb of me.
        """.trimIndent(), "Emily Dickinson"),
        Poem(28, "If I can stop one heart from breaking", """
            If I can stop one heart from breaking,
            I shall not live in vain;
            If I can ease one life the aching,
            Or cool one pain,
            Or help one fainting robin
            Unto his nest again,
            I shall not live in vain.
        """.trimIndent(), "Emily Dickinson"),
        Poem(29, "The Bustle in a House", """
            The bustle in a house
            The morning after death
            Is solemnest of industries
            Enacted upon earth, —

            The sweeping up the heart,
            And putting love away
            We shall not want to use again
            Until eternity.
        """.trimIndent(), "Emily Dickinson"),
        Poem(30, "I'm Nobody! Who are you?", """
            I'm Nobody! Who are you?
            Are you – Nobody – too?
            Then there's a pair of us!
            Don't tell! they'd advertise – you know!

            How dreary – to be – Somebody!
            How public – like a Frog –
            To tell one's name – the livelong June –
            To an admiring Bog!
        """.trimIndent(), "Emily Dickinson"),
        Poem(31, "My Life Closed Twice Before Its Close", """
            My life closed twice before its close;
            It yet remains to see
            If Immortality unveil
            A third event to me,

            So huge, so hopeless to conceive,
            As these that twice befell.
            Parting is all we know of heaven,
            And all we need of hell.
        """.trimIndent(), "Emily Dickinson"),
        Poem(32, "Invictus", """
            Out of the night that covers me,
            Black as the pit from pole to pole,
            I thank whatever gods may be
            For my unconquerable soul.

            In the fell clutch of circumstance
            I have not winced nor cried aloud.
            Under the bludgeonings of chance
            My head is bloody, but unbowed.

            Beyond this place of wrath and tears
            Looms but the Horror of the shade,
            And yet the menace of the years
            Finds and shall find me unafraid.

            It matters not how strait the gate,
            How charged with punishments the scroll,
            I am the master of my fate,
            I am the captain of my soul.
        """.trimIndent(), "William Ernest Henley"),
        Poem(33, "Remember", """
            Remember me when I am gone away,
            Gone far away into the silent land;
            When you can no more hold me by the hand,
            Nor I half turn to go, yet turning stay.
            Remember me when no more day by day
            You tell me of our future that you planned:
            Only remember me; you understand
            It will be late to counsel then or pray.
            Yet if you should forget me for a while
            And afterwards remember, do not grieve:
            For if the darkness and corruption leave
            A vestige of the thoughts that once I had,
            Better by far you should forget and smile
            Than that you should remember and be sad.
        """.trimIndent(), "Christina Rossetti"),
        Poem(34, "A Birthday", """
            My heart is like a singing bird
            Whose nest is in a water'd shoot;
            My heart is like an apple-tree
            Whose boughs are bent with thick-set fruit;
            My heart is like a rainbow shell
            That paddles in a halcyon sea;
            My heart is gladder than all these
            Because my love is come to me.

            Raise me a dais of silk and down;
            Hang it with vair and purple dyes;
            Carve it in doves and pomegranates,
            And peacocks with a hundred eyes;
            Work it in gold and silver grapes,
            In leaves and silver fleurs-de-lys;
            Because the birthday of my life
            Is come, my love is come to me.
        """.trimIndent(), "Christina Rossetti"),
        Poem(35, "Echo", """
            Come to me in the silence of the night;
            Come in the speaking silence of a dream;
            Come with soft rounded cheeks and eyes as bright
            As sunlight on a stream;
            Come back in tears,
            O memory, hope, love of finished years.

            O dream how sweet, too sweet, too bitter sweet,
            Whose wakening should have been in Paradise,
            Where souls brimful of love abide and meet;
            Where thirsting longing eyes
            Watch the slow door
            That opening, letting in, lets out no more.

            Yet come to me in dreams, that I may live
            My very life again though cold in death:
            Come back to me in dreams, that I may give
            Pulse for pulse, breath for breath:
            Speak low, lean low,
            As long ago, my love, how long ago.
        """.trimIndent(), "Christina Rossetti"),
        Poem(36, "Outwitted", """
            He drew a circle that shut me out —
            Heretic, rebel, a thing to flout.
            But Love and I had the wit to win:
            We drew a circle that took him in!
        """.trimIndent(), "Edwin Markham"),
        Poem(37, "There Will Come Soft Rains", """
            There will come soft rains and the smell of the ground,
            And swallows circling with their shimmering sound;

            And frogs in the pools singing at night,
            And wild plum trees in tremulous white;

            Robins will wear their feathery fire,
            Whistling their whims on a low fence-wire;

            And not one will know of the war, not one
            Will care at last when it is done.

            Not one would mind, neither bird nor tree,
            If mankind perished utterly;

            And Spring herself, when she woke at dawn
            Would scarcely know that we were gone.
        """.trimIndent(), "Sara Teasdale"),
        Poem(38, "Barter", """
            Life has loveliness to sell,
            All beautiful and splendid things,
            Blue waves whitened on a cliff,
            Soaring fire that sways and sings,
            And children's faces looking up
            Holding wonder like a cup.

            Life has loveliness to sell,
            Music like a curve of gold,
            Scent of pine trees in the rain,
            Eyes that love you, arms that hold,
            And for your spirit's still delight,
            Holy thoughts that star the night.

            Spend all you have for loveliness,
            Buy it and never count the cost;
            For one white singing hour of peace
            Count many a year of strife well lost,
            And for a breath of ecstasy
            Give all you have been, or could be.
        """.trimIndent(), "Sara Teasdale"),
        Poem(39, "Stars", """
            Alone in the night
            On a dark hill
            With pines around me
            Spicy and still,

            And a heaven full of stars
            Over my head,
            White and topaz
            And misty red;

            Myriads with beating
            Hearts of fire
            That aeons
            Cannot vex or tire;

            Up the dome of heaven
            Like a great hill,
            I watch them marching
            Stately and still,

            And I know that I
            Am honored to be
            Witness
            Of so much majesty.
        """.trimIndent(), "Sara Teasdale"),
        Poem(40, "Afternoon on a Hill", """
            I will be the gladdest thing
            Under the sun!
            I will touch a hundred flowers
            And not pick one.

            I will look at cliffs and clouds
            With quiet eyes,
            Watch the wind bow down the grass,
            And the grass rise.

            And when lights begin to show
            Up from the town,
            I will mark which must be mine,
            And then start down!
        """.trimIndent(), "Edna St. Vincent Millay"),
        Poem(41, "Travel", """
            The railroad track is miles away,
            And the day is loud with voices speaking,
            Yet there isn't a train goes by all day
            But I hear its whistle shrieking.

            All night there isn't a train goes by,
            Though the night is still for sleep and dreaming,
            But I see its cinders red on the sky,
            And hear its engine steaming.

            My heart is warm with the friends I make,
            And better friends I'll not be knowing;
            Yet there isn't a train I wouldn't take,
            No matter where it's going.
        """.trimIndent(), "Edna St. Vincent Millay"),
        Poem(42, "Requiem", """
            Under the wide and starry sky,
            Dig the grave and let me lie.
            Glad did I live and gladly die,
            And I laid me down with a will.

            This be the verse you grave for me:
            Here he lies where he longed to be;
            Home is the sailor, home from sea,
            And the hunter home from the hill.
        """.trimIndent(), "Robert Louis Stevenson"),
        Poem(43, "Leisure", """
            What is this life if, full of care,
            We have no time to stand and stare.

            No time to stand beneath the boughs
            And stare as long as sheep or cows.

            No time to see, when woods we pass,
            Where squirrels hide their nuts in grass.

            No time to see, in broad daylight,
            Streams full of stars, like skies at night.

            No time to turn at Beauty's glance,
            And watch her feet, how they can dance.

            No time to wait till her mouth can
            Enrich that smile her eyes began.

            A poor life this if, full of care,
            We have no time to stand and stare.
        """.trimIndent(), "W. H. Davies"),
        Poem(44, "Sea Fever", """
            I must go down to the seas again, to the lonely sea and the sky,
            And all I ask is a tall ship and a star to steer her by;
            And the wheel's kick and the wind's song and the white sail's shaking,
            And a grey mist on the sea's face, and a grey dawn breaking.

            I must go down to the seas again, for the call of the running tide
            Is a wild call and a clear call that may not be denied;
            And all I ask is a windy day with the white clouds flying,
            And the flung spray and the blown spume, and the sea-gulls crying.

            I must go down to the seas again, to the vagrant gypsy life,
            To the gull's way and the whale's way where the wind's like a whetted knife;
            And all I ask is a merry yarn from a laughing fellow-rover,
            And quiet sleep and a sweet dream when the long trick's over.
        """.trimIndent(), "John Masefield"),
        Poem(45, "Loveliest of Trees", """
            Loveliest of trees, the cherry now
            Is hung with bloom along the bough,
            And stands about the woodland ride
            Wearing white for Eastertide.

            Now, of my threescore years and ten,
            Twenty will not come again,
            And take from seventy springs a score,
            It only leaves me fifty more.

            And since to look at things in bloom
            Fifty springs are little room,
            About the woodlands I will go
            To see the cherry hung with snow.
        """.trimIndent(), "A. E. Housman"),
        Poem(46, "With Rue My Heart Is Laden", """
            With rue my heart is laden
            For golden friends I had,
            For many a rose-lipt maiden
            And many a lightfoot lad.

            By brooks too broad for leaping
            The lightfoot boys are laid;
            The rose-lipt girls are sleeping
            In fields where roses fade.
        """.trimIndent(), "A. E. Housman"),
        Poem(47, "The Clod and the Pebble", """
            "Love seeketh not itself to please,
            Nor for itself hath any care,
            But for another gives its ease,
            And builds a Heaven in Hell's despair."

            So sung a little Clod of Clay,
            Trodden with the cattle's feet,
            But a Pebble of the brook
            Warbled out these metres meet:

            "Love seeketh only Self to please,
            To bind another to its delight,
            Joys in another's loss of ease,
            And builds a Hell in Heaven's despite."
        """.trimIndent(), "William Blake"),
        Poem(48, "Laughing Song", """
            When the green woods laugh with the voice of joy,
            And the dimpling stream runs laughing by;
            When the air does laugh with our merry wit,
            And the green hill laughs with the noise of it;

            When the meadows laugh with lively green,
            And the grasshopper laughs in the merry scene;
            When Mary and Susan and Emily
            With their sweet round mouths sing "Ha, ha, he!"

            When the painted birds laugh in the shade,
            Where our table with cherries and nuts is spread:
            Come live, and be merry, and join with me,
            To sing the sweet chorus of "Ha, ha, he!"
        """.trimIndent(), "William Blake"),
        Poem(49, "Say Not the Struggle Nought Availeth", """
            Say not the struggle nought availeth,
            The labour and the wounds are vain,
            The enemy faints not, nor faileth,
            And as things have been they remain.

            If hopes were dupes, fears may be liars;
            It may be, in yon smoke concealed,
            Your comrades chase e'en now the fliers,
            And, but for you, possess the field.

            For while the tired waves, vainly breaking,
            Seem here no painful inch to gain,
            Far back, through creeks and inlets making,
            Comes silent, flooding in, the main.

            And not by eastern windows only,
            When daylight comes, comes in the light;
            In front the sun climbs slow, how slowly!
            But westward, look, the land is bright!
        """.trimIndent(), "Arthur Hugh Clough"),
        Poem(50, "The Pasture", """
            I'm going out to clean the pasture spring;
            I'll only stop to rake the leaves away
            (And wait to watch the water clear, I may):
            I sha'n't be gone long. — You come too.

            I'm going out to fetch the little calf
            That's standing by the mother. It's so young,
            It totters when she licks it with her tongue.
            I sha'n't be gone long. — You come too.
        """.trimIndent(), "Robert Frost"),
        Poem(51, "Dust of Snow", """
            The way a crow
            Shook down on me
            The dust of snow
            From a hemlock tree

            Has given my heart
            A change of mood
            And saved some part
            Of a day I had rued.
        """.trimIndent(), "Robert Frost"),
        Poem(52, "Ozymandias", """
            I met a traveller from an antique land
            Who said: Two vast and trunkless legs of stone
            Stand in the desert... Near them, on the sand,
            Half sunk, a shattered visage lies, whose frown,
            And wrinkled lip, and sneer of cold command,
            Tell that its sculptor well those passions read
            Which yet survive, stamped on these lifeless things,
            The hand that mocked them, and the heart that fed;
            And on the pedestal these words appear:
            "My name is Ozymandias, king of kings:
            Look on my works, ye Mighty, and despair!"
            Nothing beside remains. Round the decay
            Of that colossal wreck, boundless and bare
            The lone and level sands stretch far away.
        """.trimIndent(), "Percy Bysshe Shelley"),
        Poem(53, "The World Is Too Much with Us", """
            The world is too much with us; late and soon,
            Getting and spending, we lay waste our powers;—
            Little we see in Nature that is ours;
            We have given our hearts away, a sordid boon!
            This Sea that bares her bosom to the moon;
            The winds that will be howling at all hours,
            And are up-gathered now like sleeping flowers;
            For this, for everything, we are out of tune;
            It moves us not. Great God! I'd rather be
            A Pagan suckled in a creed outworn;
            So might I, standing on this pleasant lea,
            Have glimpses that would make me less forlorn;
            Have sight of Proteus rising from the sea;
            Or hear old Triton blow his wreathèd horn.
        """.trimIndent(), "William Wordsworth"),
        Poem(54, "My Heart Leaps Up", """
            My heart leaps up when I behold
            A rainbow in the sky:
            So was it when my life began;
            So is it now I am a man;
            So be it when I shall grow old,
            Or let me die!
            The Child is father of the Man;
            And I could wish my days to be
            Bound each to each by natural piety.
        """.trimIndent(), "William Wordsworth"),
        Poem(55, "The Darkling Thrush", """
            I leant upon a coppice gate
            When Frost was spectre-grey,
            And Winter's dregs made desolate
            The weakening eye of day.
            The tangled bine-stems scored the sky
            Like strings of broken lyres,
            And all mankind that haunted nigh
            Had sought their household fires.

            The land's sharp features seemed to be
            The Century's corpse outleant,
            His crypt the cloudy canopy,
            The wind his death-lament.
            The ancient pulse of germ and birth
            Was shrunken hard and dry,
            And every spirit upon earth
            Seemed fervourless as I.

            At once a voice arose among
            The bleak twigs overhead
            In a full-hearted evensong
            Of joy illimited;
            An aged thrush, frail, gaunt, and small,
            In blast-beruffled plume,
            Had chosen thus to fling his soul
            Upon the growing gloom.

            So little cause for carolings
            Of such ecstatic sound
            Was written on terrestrial things
            Afar or nigh around,
            That I could think there trembled through
            His happy good-night air
            Some blessed Hope, whereof he knew
            And I was unaware.
        """.trimIndent(), "Thomas Hardy"),
        Poem(56, "How Do I Love Thee? (Sonnet 43)", """
            How do I love thee? Let me count the ways.
            I love thee to the depth and breadth and height
            My soul can reach, when feeling out of sight
            For the ends of being and ideal grace.
            I love thee to the level of every day's
            Most quiet need, by sun and candle-light.
            I love thee freely, as men strive for right;
            I love thee purely, as they turn from praise.
            I love thee with the passion put to use
            In my old griefs, and with my childhood's faith.
            I love thee with a love I seemed to lose
            With my lost saints. I love thee with the breath,
            Smiles, tears, of all my life; and, if God choose,
            I shall but love thee better after death.
        """.trimIndent(), "Elizabeth Barrett Browning"),
        Poem(57, "Sonnet 116", """
            Let me not to the marriage of true minds
            Admit impediments. Love is not love
            Which alters when it alteration finds,
            Or bends with the remover to remove.
            O no! it is an ever-fixed mark
            That looks on tempests and is never shaken;
            It is the star to every wandering bark,
            Whose worth's unknown, although his height be taken.
            Love's not Time's fool, though rosy lips and cheeks
            Within his bending sickle's compass come;
            Love alters not with his brief hours and weeks,
            But bears it out even to the edge of doom.
            If this be error and upon me proved,
            I never writ, nor no man ever loved.
        """.trimIndent(), "William Shakespeare"),
        Poem(58, "Since There's No Help", """
            Since there's no help, come let us kiss and part—
            Nay, I have done, you get no more of me;
            And I am glad, yea glad with all my heart,
            That thus so cleanly I myself can free.
            Shake hands for ever, cancel all our vows,
            And when we meet at any time again,
            Be it not seen in either of our brows
            That we one jot of former love retain.
            Now at the last gasp of Love's latest breath,
            When, his pulse failing, Passion speechless lies;
            When Faith is kneeling by his bed of death,
            And Innocence is closing up his eyes—
            Now, if thou wouldst, when all have given him over,
            From death to life thou might'st him yet recover.
        """.trimIndent(), "Michael Drayton"),
        Poem(59, "The Eagle", """
            He clasps the crag with crooked hands;
            Close to the sun in lonely lands,
            Ring'd with the azure world, he stands.

            The wrinkled sea beneath him crawls;
            He watches from his mountain walls,
            And like a thunderbolt he falls.
        """.trimIndent(), "Alfred, Lord Tennyson"),
        Poem(60, "Fall, Leaves, Fall", """
            Fall, leaves, fall; die, flowers, away;
            Lengthen night and shorten day;
            Every leaf speaks bliss to me
            Fluttering from the autumn tree.

            I shall smile when wreaths of snow
            Blossom where the rose should grow;
            I shall sing when night's decay
            Ushers in a drearier day.
        """.trimIndent(), "Emily Brontë"),
        Poem(61, "Solitude", """
            Laugh, and the world laughs with you;
            Weep, and you weep alone.
            For the sad old earth must borrow its mirth,
            But has trouble enough of its own.

            Sing, and the hills will answer;
            Sigh, it is lost on the air.
            The echoes bound to a joyful sound,
            But shrink from voicing care.

            Rejoice, and men will seek you;
            Grieve, and they turn and go.
            They want full measure of all your pleasure,
            But they do not need your woe.

            Be glad, and your friends are many;
            Be sad, and you lose them all.
            There are none to decline your nectared wine,
            But alone you must drink life's gall.

            Feast, and your halls are crowded;
            Fast, and the world goes by.
            Succeed and give, and it helps you live,
            But no man can help you die.

            There is room in the halls of pleasure
            For a large and lordly train,
            But one by one we must all file on
            Through the narrow aisles of pain.
        """.trimIndent(), "Ella Wheeler Wilcox"),
        Poem(62, "Silver", """
            Slowly, silently, now the moon
            Walks the night in her silver shoon;
            This way, and that, she peers, and sees
            Silver fruit upon silver trees;
            One by one the casements catch
            Her beams beneath the silvery thatch;
            Couched in his kennel, like a log,
            With paws of silver sleeps the dog;
            From their shadowy cote the white breasts peep
            Of doves in a silver-feathered sleep;
            A harvest mouse goes scampering by,
            With silver claws, and silver eye;
            And moveless fish in the water gleam,
            By silver reeds in a silver stream.
        """.trimIndent(), "Walter de la Mare"),
        Poem(63, "Adlestrop", """
            Yes. I remember Adlestrop—
            The name, because one afternoon
            Of heat the express-train drew up there
            Unwontedly. It was late June.

            The steam hissed. Someone cleared his throat.
            No one left and no one came
            On the bare platform. What I saw
            Was Adlestrop—only the name

            And willows, willow-herb, and grass,
            And meadowsweet, and haycocks dry,
            No whit less still and lonely fair
            Than the high cloudlets in the sky.

            And for that minute a blackbird sang
            Close by, and round him, mistier,
            Farther and farther, all the birds
            Of Oxfordshire and Gloucestershire.
        """.trimIndent(), "Edward Thomas"),
        Poem(64, "Fog", """
            The fog comes
            on little cat feet.

            It sits looking
            over harbor and city
            on silent haunches
            and then moves on.
        """.trimIndent(), "Carl Sandburg"),
        Poem(65, "Grass", """
            Pile the bodies high at Austerlitz and Waterloo.
            Shovel them under and let me work—
            I am the grass; I cover all.

            And pile them high at Gettysburg
            And pile them high at Ypres and Verdun.
            Shovel them under and let me work.
            Two years, ten years, and passengers ask the conductor:
            What place is this?
            Where are we now?

            I am the grass.
            Let me work.
        """.trimIndent(), "Carl Sandburg"),
        Poem(66, "Futility", """
            Move him into the sun—
            Gently its touch awoke him once,
            At home, whispering of fields half-sown.
            Always it woke him, even in France,
            Until this morning and this snow.
            If anything might rouse him now
            The kind old sun will know.

            Think how it wakes the seeds—
            Woke once the clays of a cold star.
            Are limbs, so dear-achieved, are sides
            Full-nerved—still warm—too hard to stir?
            Was it for this the clay grew tall?
            —O what made fatuous sunbeams toil
            To break earth's sleep at all?
        """.trimIndent(), "Wilfred Owen"),
        Poem(67, "To My Dear and Loving Husband", """
            If ever two were one, then surely we.
            If ever man were loved by wife, then thee.
            If ever wife was happy in a man,
            Compare with me, ye women, if you can.
            I prize thy love more than whole mines of gold,
            Or all the riches that the East doth hold.
            My love is such that rivers cannot quench,
            Nor ought but love from thee give recompense.
            Thy love is such I can no way repay;
            The heavens reward thee manifold, I pray.
            Then while we live, in love let's so persever,
            That when we live no more, we may live ever.
        """.trimIndent(), "Anne Bradstreet"),
        Poem(68, "On My First Son", """
            Farewell, thou child of my right hand, and joy;
            My sin was too much hope of thee, loved boy.
            Seven years thou wert lent to me, and I thee pay,
            Exacted by thy fate, on the just day.
            O, could I lose all father now! For why
            Will man lament the state he should envy?
            To have so soon 'scaped world's and flesh's rage,
            And, if no other misery, yet age?
            Rest in soft peace, and, asked, say, "Here doth lie
            Ben Jonson his best piece of poetry."
            For whose sake henceforth all his vows be such
            As what he loves may never like too much.
        """.trimIndent(), "Ben Jonson"),
        Poem(69, "On the Grasshopper and Cricket", """
            The poetry of earth is never dead:
            When all the birds are faint with the hot sun,
            And hide in cooling trees, a voice will run
            From hedge to hedge about the new-mown mead;
            That is the Grasshopper's — he takes the lead
            In summer luxury, — he has never done
            With his delights; for when tired out with fun
            He rests at ease beneath some pleasant weed.
            The poetry of earth is ceasing never:
            On a lone winter evening, when the frost
            Has wrought a silence, from the stove there shrills
            The Cricket's song, in warmth increasing ever,
            And seems to one in drowsiness half lost,
            The Grasshopper's among some grassy hills.
        """.trimIndent(), "John Keats"),
        Poem(70, "Little Trotty Wagtail", """
            Little trotty wagtail, he went in the rain,
            And twittering, tottering sideways he ne'er got straight again.
            He stooped to get a worm, and looked up to get a fly,
            And then he flew away ere his feathers they were dry.

            Little trotty wagtail, he waddled in the mud,
            And left his little footmarks, trample where he would.
            He waddled in the water-pudge, and waggle went his tail,
            And chirrupt up his wings to dry upon the garden rail.

            Little trotty wagtail, you nimble all about,
            And in the dimpling water-pudge you waddle in and out;
            Your home is nigh at hand, and in the warm pig-stye,
            So, little Master Wagtail, I'll bid you a "Good-bye."
        """.trimIndent(), "John Clare"),
        Poem(71, "Spring, the Sweet Spring", """
            Spring, the sweet spring, is the year's pleasant king;
            Then blooms each thing, then maids dance in a ring,
            Cold doth not sting, the pretty birds do sing:
            Cuckoo, jug-jug, pu-we, to-witta-woo!

            The palm and may make country houses gay,
            Lambs frisk and play, the shepherds pipe all day,
            And we hear aye birds tune this merry lay:
            Cuckoo, jug-jug, pu-we, to-witta-woo!

            The fields breathe sweet, the daisies kiss our feet,
            Young lovers meet, old wives a-sunning sit,
            In every street these tunes our ears do greet:
            Cuckoo, jug-jug, pu-we, to-witta-woo!
            Spring, the sweet spring!
        """.trimIndent(), "Thomas Nashe"),
        Poem(72, "Home-Thoughts, from Abroad", """
            Oh, to be in England
            Now that April's there,
            And whoever wakes in England
            Sees, some morning, unaware,
            That the lowest boughs and the brushwood sheaf
            Round the elm-tree bole are in tiny leaf,
            While the chaffinch sings on the orchard bough
            In England — now!

            And after April, when May follows,
            And the whitethroat builds, and all the swallows!
            Hark, where my blossomed pear-tree in the hedge
            Leans to the field and scatters on the clover
            Blossoms and dewdrops — at the bent spray's edge —
            That's the wise thrush; he sings each song twice over,
            Lest you should think he never could recapture
            The first fine careless rapture!
            And though the fields look rough with hoary dew,
            All will be gay when noontide wakes anew
            The buttercups, the little children's dower
            — Far brighter than this gaudy melon-flower!
        """.trimIndent(), "Robert Browning"),
        Poem(73, "Autumn Fires", """
            In the other gardens
            And all up the vale,
            From the autumn bonfires
            See the smoke trail!

            Pleasant summer over
            And all the summer flowers,
            The red fire blazes,
            The grey smoke towers.

            Sing a song of seasons!
            Something bright in all!
            Flowers in the summer,
            Fires in the fall!
        """.trimIndent(), "Robert Louis Stevenson"),
        Poem(74, "Tall Nettles", """
            Tall nettles cover up, as they have done
            These many springs, the rusty harrow, the plough
            Long worn out, and the roller made of stone:
            Only the elm butt tops the nettles now.

            This corner of the farmyard I like most:
            As well as any bloom upon a flower
            I like the dust on the nettles, never lost
            Except to prove the sweetness of a shower.
        """.trimIndent(), "Edward Thomas"),
        Poem(75, "Who Has Seen the Wind?", """
            Who has seen the wind?
            Neither I nor you:
            But when the leaves hang trembling,
            The wind is passing through.

            Who has seen the wind?
            Neither you nor I:
            But when the trees bow down their heads,
            The wind is passing by.
        """.trimIndent(), "Christina Rossetti"),
        Poem(76, "Reapers", """
            Black reapers with the sound of steel on stones
            Are sharpening scythes. I see them place the hones
            In their hip-pockets as a thing that's done,
            And start their silent swinging, one by one.
            Black horses drive a mower through the weeds,
            And there, a field rat, startled, squealing bleeds,
            His belly close to ground. I see the blade,
            Blood-stained, continue cutting weeds and shade.
        """.trimIndent(), "Jean Toomer"),
        Poem(77, "Indian Weavers", """
            Weavers, weaving at break of day,
            Why do you weave a garment so gay?
            Blue as the wing of a halcyon wild,
            We weave the robes of a new-born child.

            Weavers, weaving at fall of night,
            Why do you weave a garment so bright?
            Like the plumes of a peacock, purple and green,
            We weave the marriage-veils of a queen.

            Weavers, weaving solemn and still,
            What do you weave in the moonlight chill?
            White as a feather and white as a cloud,
            We weave a dead man's funeral shroud.
        """.trimIndent(), "Sarojini Naidu"),
        Poem(78, "The New Colossus", """
            Not like the brazen giant of Greek fame,
            With conquering limbs astride from land to land;
            Here at our sea-washed, sunset gates shall stand
            A mighty woman with a torch, whose flame
            Is the imprisoned lightning, and her name
            Mother of Exiles. From her beacon-hand
            Glows world-wide welcome; her mild eyes command
            The air-bridged harbor that twin cities frame.
            "Keep, ancient lands, your storied pomp!" cries she
            With silent lips. "Give me your tired, your poor,
            Your huddled masses yearning to breathe free,
            The wretched refuse of your teeming shore.
            Send these, the homeless, tempest-tost to me,
            I lift my lamp beside the golden door!"
        """.trimIndent(), "Emma Lazarus"),
        Poem(79, "America", """
            Although she feeds me bread of bitterness,
            And sinks into my throat her tiger's tooth,
            Stealing my breath of life, I will confess
            I love this cultured hell that tests my youth.
            Her vigor flows like tides into my blood,
            Giving me strength erect against her hate,
            Her bigness sweeps my being like a flood.
            Yet, as a rebel fronts a king in state,
            I stand within her walls with not a shred
            Of terror, malice, not a word of jeer.
            Darkly I gaze into the days ahead,
            And see her might and granite wonders there,
            Beneath the touch of Time's unerring hand,
            Like priceless treasures sinking in the sand.
        """.trimIndent(), "Claude McKay"),
        Poem(80, "Saturday's Child", """
            Some are teethed on a silver spoon,
            With the stars strung for a rattle;
            I cut my teeth as the black raccoon —
            For implements of battle.

            Some are swaddled in silk and down,
            And heralded by a star;
            They swathed my limbs in a sackcloth gown
            On a night that was black as tar.

            For some, godfather and goddame
            The opulent fairies be;
            Dame Poverty gave me my name,
            And Pain godfathered me.

            For I was born on Saturday —
            "Bad time for planting a seed,"
            Was all my father had to say,
            And, "One mouth more to feed."

            Death cut the strings that gave me life,
            And handed me to Sorrow,
            The only kind of middle wife
            My folks could beg or borrow.
        """.trimIndent(), "Countee Cullen"),
        Poem(81, "The Heart of a Woman", """
            The heart of a woman goes forth with the dawn,
            As a lone bird, soft winging, so restlessly on,
            Afar o'er life's turrets and vales does it roam
            In the wake of those echoes the heart calls home.

            The heart of a woman falls back with the night,
            And enters some alien cage in its plight,
            And tries to forget it has dreamed of the stars
            While it breaks, breaks, breaks on the sheltering bars.
        """.trimIndent(), "Georgia Douglas Johnson"),
        Poem(82, "Break, Break, Break", """
            Break, break, break,
            On thy cold gray stones, O Sea!
            And I would that my tongue could utter
            The thoughts that arise in me.

            O, well for the fisherman's boy,
            That he shouts with his sister at play!
            O, well for the sailor lad,
            That he sings in his boat on the bay!

            And the stately ships go on
            To their haven under the hill;
            But O for the touch of a vanish'd hand,
            And the sound of a voice that is still!

            Break, break, break,
            At the foot of thy crags, O Sea!
            But the tender grace of a day that is dead
            Will never come back to me.
        """.trimIndent(), "Alfred, Lord Tennyson"),
        Poem(83, "If Thou Must Love Me (Sonnet 14)", """
            If thou must love me, let it be for nought
            Except for love's sake only. Do not say
            "I love her for her smile — her look — her way
            Of speaking gently, — for a trick of thought
            That falls in well with mine, and certes brought
            A sense of pleasant ease on such a day" —
            For these things in themselves, Belovèd, may
            Be changed, or change for thee, — and love, so wrought,
            May be unwrought so. Neither love me for
            Thine own dear pity's wiping my cheeks dry, —
            A creature might forget to weep, who bore
            Thy comfort long, and lose thy love thereby!
            But love me for love's sake, that evermore
            Thou mayst love on, through love's eternity.
        """.trimIndent(), "Elizabeth Barrett Browning"),
        Poem(84, "The Tide Rises, the Tide Falls", """
            The tide rises, the tide falls,
            The twilight darkens, the curlew calls;
            Along the sea-sands damp and brown
            The traveller hastens toward the town,
            And the tide rises, the tide falls.

            Darkness settles on roofs and walls,
            But the sea, the sea in darkness calls;
            The little waves, with their soft, white hands,
            Efface the footprints in the sands,
            And the tide rises, the tide falls.

            The morning breaks; the steeds in their stalls
            Stamp and neigh, as the hostler calls;
            The day returns, but nevermore
            Returns the traveller to the shore,
            And the tide rises, the tide falls.
        """.trimIndent(), "Henry Wadsworth Longfellow"),
        Poem(85, "Into My Heart an Air That Kills", """
            Into my heart an air that kills
            From yon far country blows:
            What are those blue remembered hills,
            What spires, what farms are those?

            That is the land of lost content,
            I see it shining plain,
            The happy highways where I went
            And cannot come again.
        """.trimIndent(), "A. E. Housman"),
        Poem(86, "Life", """
            Life, believe, is not a dream
            So dark as sages say;
            Oft a little morning rain
            Foretells a pleasant day.

            Sometimes there are clouds of gloom,
            But these are transient all;
            If the shower will make the roses bloom,
            O why lament its fall?

            Rapidly, merrily,
            Life's sunny hours flit by,
            Gratefully, cheerily,
            Enjoy them as they fly!

            What though Death at times steps in
            And calls our Best away?
            What though sorrow seems to win,
            O'er hope, a heavy sway?

            Yet Hope again elastic springs,
            Unconquered, though she fell;
            Still buoyant are her golden wings,
            Still strong to bear us well.

            Manfully, fearlessly,
            The day of trial bear,
            For gloriously, victoriously,
            Can courage quell despair!
        """.trimIndent(), "Charlotte Brontë"),
        Poem(87, "The Rainy Day", """
            The day is cold, and dark, and dreary;
            It rains, and the wind is never weary;
            The vine still clings to the mouldering wall,
            But at every gust the dead leaves fall,
            And the day is dark and dreary.

            My life is cold, and dark, and dreary;
            It rains, and the wind is never weary;
            My thoughts still cling to the mouldering Past,
            But the hopes of youth fall thick in the blast,
            And the days are dark and dreary.

            Be still, sad heart! and cease repining;
            Behind the clouds is the sun still shining;
            Thy fate is the common fate of all,
            Into each life some rain must fall,
            Some days must be dark and dreary.
        """.trimIndent(), "Henry Wadsworth Longfellow"),
        Poem(88, "On His Seventy-Fifth Birthday", """
            I strove with none, for none was worth my strife.
            Nature I loved, and, next to Nature, Art:
            I warm'd both hands before the fire of life;
            It sinks, and I am ready to depart.
        """.trimIndent(), "Walter Savage Landor"),
        Poem(89, "Everyone Sang", """
            Everyone suddenly burst out singing;
            And I was filled with such delight
            As prisoned birds must find in freedom,
            Winging wildly across the white
            Orchards and dark-green fields; on — on — and out of sight.

            Everyone's voice was suddenly lifted;
            And beauty came like the setting sun:
            My heart was shaken with tears; and horror
            Drifted away ... O, but Everyone
            Was a bird; and the song was wordless; the singing will never be done.
        """.trimIndent(), "Siegfried Sassoon"),
        Poem(90, "Recuerdo", """
            We were very tired, we were very merry —
            We had gone back and forth all night on the ferry.
            It was bare and bright, and smelled like a stable —
            But we looked into a fire, we leaned across a table,
            We lay on a hill-top underneath the moon;
            And the whistles kept blowing, and the dawn came soon.

            We were very tired, we were very merry —
            We had gone back and forth all night on the ferry;
            And you ate an apple, and I ate a pear,
            From a dozen of each we had bought somewhere;
            And the sky went wan, and the wind came cold,
            And the sun rose dripping, a bucketful of gold.

            We were very tired, we were very merry,
            We had gone back and forth all night on the ferry.
            We hailed "Good morrow, mother!" to a shawl-covered head,
            And bought a morning paper, which neither of us read;
            And she wept, "God bless you!" for the apples and pears,
            And we gave her all our money but our subway fares.
        """.trimIndent(), "Edna St. Vincent Millay"),
        Poem(91, "A Little Song of Life", """
            Glad that I live am I;
            That the sky is blue;
            Glad for the country lanes,
            And the fall of dew.

            After the sun the rain;
            After the rain the sun;
            This is the way of life,
            Till the work be done.

            All that we need to do,
            Be we low or high,
            Is to see that we grow
            Nearer the sky.
        """.trimIndent(), "Lizette Woodworth Reese"),
        Poem(92, "Jenny Kiss'd Me", """
            Jenny kiss'd me when we met,
            Jumping from the chair she sat in;
            Time, you thief, who love to get
            Sweets into your list, put that in!
            Say I'm weary, say I'm sad,
            Say that health and wealth have miss'd me,
            Say I'm growing old, but add,
            Jenny kiss'd me.
        """.trimIndent(), "Leigh Hunt"),
        Poem(93, "In Time of 'The Breaking of Nations'", """
            Only a man harrowing clods
            In a slow silent walk
            With an old horse that stumbles and nods
            Half asleep as they stalk.

            Only thin smoke without flame
            From the heaps of couch-grass;
            Yet this will go onward the same
            Though Dynasties pass.

            Yonder a maid and her wight
            Come whispering by:
            War's annals will cloud into night
            Ere their story die.
        """.trimIndent(), "Thomas Hardy"),
        Poem(94, "The Rhodora", """
            In May, when sea-winds pierced our solitudes,
            I found the fresh Rhodora in the woods,
            Spreading its leafless blooms in a damp nook,
            To please the desert and the sluggish brook.
            The purple petals, fallen in the pool,
            Made the black water with their beauty gay;
            Here might the red-bird come his plumes to cool,
            And court the flower that cheapens his array.
            Rhodora! if the sages ask thee why
            This charm is wasted on the earth and sky,
            Tell them, dear, that if eyes were made for seeing,
            Then Beauty is its own excuse for being:
            Why thou wert there, O rival of the rose!
            I never thought to ask, I never knew:
            But, in my simple ignorance, suppose
            The self-same Power that brought me there brought you.
        """.trimIndent(), "Ralph Waldo Emerson"),
        Poem(95, "Fable", """
            The mountain and the squirrel
            Had a quarrel;
            And the former called the latter "Little Prig."
            Bun replied,
            "You are doubtless very big;
            But all sorts of things and weather
            Must be taken in together,
            To make up a year
            And a sphere.
            And I think it no disgrace
            To occupy my place.
            If I'm not so large as you,
            You are not so small as I,
            And not half so spry.
            I'll not deny you make
            A very pretty squirrel track;
            Talents differ; all is well and wisely put;
            If I cannot carry forests on my back,
            Neither can you crack a nut."
        """.trimIndent(), "Ralph Waldo Emerson"),
        Poem(96, "A Man Said to the Universe", """
            A man said to the universe:
            "Sir, I exist!"
            "However," replied the universe,
            "The fact has not created in me
            A sense of obligation."
        """.trimIndent(), "Stephen Crane"),
        Poem(97, "The Leaden-Eyed", """
            Let not young souls be smothered out before
            They do quaint deeds and fully flaunt their pride.
            It is the world's one crime its babes grow dull,
            Its poor are ox-like, limp and leaden-eyed.

            Not that they starve, but starve so dreamlessly,
            Not that they sow, but that they seldom reap,
            Not that they serve, but have no gods to serve,
            Not that they die, but that they die like sheep.
        """.trimIndent(), "Vachel Lindsay"),
        Poem(98, "A Decade", """
            When you came, you were like red wine and honey,
            And the taste of you burnt my mouth with its sweetness.
            Now you are like morning bread,
            Smooth and pleasant.
            I hardly taste you at all for I know your savour,
            But I am completely nourished.
        """.trimIndent(), "Amy Lowell"),
        Poem(99, "Wind and Silver", """
            Greatly shining,
            The Autumn moon floats in the thin sky;
            And the fish-ponds shake their backs and flash their dragon scales
            As she passes over them.
        """.trimIndent(), "Amy Lowell"),
        Poem(100, "Oread", """
            Whirl up, sea—
            whirl your pointed pines,
            splash your great pines
            on our rocks,
            hurl your green over us,
            cover us with your pools of fir.
        """.trimIndent(), "H. D."),
        Poem(101, "November Night", """
            Listen…
            With faint dry sound,
            Like steps of passing ghosts,
            The leaves, frost-crisp'd, break from the trees
            And fall.
        """.trimIndent(), "Adelaide Crapsey"),
        Poem(102, "Triad", """
            These be
            Three silent things:
            The falling snow… the hour
            Before the dawn… the mouth of one
            Just dead.
        """.trimIndent(), "Adelaide Crapsey"),
        Poem(103, "The Passionate Shepherd to His Love", """
            Come live with me and be my love,
            And we will all the pleasures prove
            That valleys, groves, hills, and fields,
            Woods, or steepy mountain yields.

            And we will sit upon the rocks,
            Seeing the shepherds feed their flocks,
            By shallow rivers to whose falls
            Melodious birds sing madrigals.

            And I will make thee beds of roses
            And a thousand fragrant posies,
            A cap of flowers, and a kirtle
            Embroidered all with leaves of myrtle;

            A gown made of the finest wool
            Which from our pretty lambs we pull;
            Fair lined slippers for the cold,
            With buckles of the purest gold;

            A belt of straw and ivy buds,
            With coral clasps and amber studs:
            And if these pleasures may thee move,
            Come live with me and be my love.

            The shepherds' swains shall dance and sing
            For thy delight each May morning:
            If these delights thy mind may move,
            Then live with me and be my love.
        """.trimIndent(), "Christopher Marlowe"),
        Poem(104, "Now Winter Nights Enlarge", """
            Now winter nights enlarge
            The number of their hours;
            And clouds their storms discharge
            Upon the airy towers.
            Let now the chimneys blaze
            And cups o'erflow with wine,
            Let well-tuned words amaze
            With harmony divine.
            Now yellow waxen lights
            Shall wait on honey love
            While youthful revels, masques, and courtly sights
            Sleep's leaden spells remove.

            This time doth well dispense
            With lovers' long discourse;
            Much speech hath some defence,
            Though beauty no remorse.
            All do not all things well;
            Some measures comely tread,
            Some knotted riddles tell,
            Some poems smoothly read.
            The summer hath his joys,
            And winter his delights;
            Though love and all his pleasures are but toys,
            They shorten tedious nights.
        """.trimIndent(), "Thomas Campion"),
        Poem(105, "Work Without Hope", """
            All Nature seems at work. Slugs leave their lair—
            The bees are stirring—birds are on the wing—
            And Winter slumbering in the open air,
            Wears on his smiling face a dream of Spring!
            And I the while, the sole unbusy thing,
            Nor honey make, nor pair, nor build, nor sing.

            Yet well I ken the banks where amaranths blow,
            Have traced the fount whence streams of nectar flow.
            Bloom, O ye amaranths! bloom for whom ye may,
            For me ye bloom not! Glide, rich streams, away!
            With lips unbrightened, wreathless brow, I stroll:
            And would you learn the spells that drowse my soul?
            Work without Hope draws nectar in a sieve,
            And Hope without an object cannot live.
        """.trimIndent(), "Samuel Taylor Coleridge"),
        Poem(106, "She Walks in Beauty", """
            She walks in beauty, like the night
            Of cloudless climes and starry skies;
            And all that's best of dark and bright
            Meet in her aspect and her eyes:
            Thus mellowed to that tender light
            Which heaven to gaudy day denies.

            One shade the more, one ray the less,
            Had half impaired the nameless grace
            Which waves in every raven tress,
            Or softly lightens o'er her face;
            Where thoughts serenely sweet express
            How pure, how dear their dwelling-place.

            And on that cheek, and o'er that brow,
            So soft, so calm, yet eloquent,
            The smiles that win, the tints that glow,
            But tell of days in goodness spent,
            A mind at peace with all below,
            A heart whose love is innocent!
        """.trimIndent(), "Lord Byron"),
        Poem(107, "Music, When Soft Voices Die", """
            Music, when soft voices die,
            Vibrates in the memory—
            Odours, when sweet violets sicken,
            Live within the sense they quicken.

            Rose leaves, when the rose is dead,
            Are heaped for the belovèd's bed;
            And so thy thoughts, when thou art gone,
            Love itself shall slumber on.
        """.trimIndent(), "Percy Bysshe Shelley"),
        Poem(108, "When I Have Fears That I May Cease to Be", """
            When I have fears that I may cease to be
            Before my pen has gleaned my teeming brain,
            Before high-pilèd books, in charactery,
            Hold like rich garners the full ripened grain;
            When I behold, upon the night's starred face,
            Huge cloudy symbols of a high romance,
            And think that I may never live to trace
            Their shadows, with the magic hand of chance;
            And when I feel, fair creature of an hour,
            That I shall never look upon thee more,
            Never have relish in the faery power
            Of unreflecting love—then on the shore
            Of the wide world I stand alone, and think
            Till love and fame to nothingness do sink.
        """.trimIndent(), "John Keats"),
        Poem(109, "The Death-Bed", """
            We watched her breathing through the night,
            Her breathing soft and low,
            As in her breast the wave of life
            Kept heaving to and fro.

            So silently we seemed to speak,
            So slowly moved about,
            As we had lent her half our powers
            To eke her being out.

            Our very hopes belied our fears,
            Our fears our hopes belied—
            We thought her dying when she slept,
            And sleeping when she died.

            For when the morn came dim and sad,
            And chill with early showers,
            Her quiet eyelids closed—she had
            Another morn than ours.
        """.trimIndent(), "Thomas Hood"),
        Poem(110, "Requiescat", """
            Strew on her roses, roses,
            And never a spray of yew!
            In quiet she reposes;
            Ah, would that I did too!

            Her mirth the world required;
            She bathed it in smiles of glee.
            But her heart was tired, tired,
            And now they let her be.

            Her life was turning, turning,
            In mazes of heat and sound.
            But for peace her soul was yearning,
            And now peace laps her round.

            Her cabined, ample spirit,
            It fluttered and failed for breath.
            To-night it doth inherit
            The vasty hall of death.
        """.trimIndent(), "Matthew Arnold"),
        Poem(111, "Vitae Summa Brevis", """
            They are not long, the weeping and the laughter,
            Love and desire and hate:
            I think they have no portion in us after
            We pass the gate.

            They are not long, the days of wine and roses:
            Out of a misty dream
            Our path emerges for a while, then closes
            Within a dream.
        """.trimIndent(), "Ernest Dowson"),
        Poem(112, "Renouncement", """
            I must not think of thee; and, tired yet strong,
            I shun the thought that lurks in all delight—
            The thought of thee—and in the blue heaven's height,
            And in the sweetest passage of a song.
            Oh, just beyond the fairest thoughts that throng
            This breast, the thought of thee waits, hidden yet bright;
            But it must never, never come in sight;
            I must stop short of thee the whole day long.
            But when sleep comes to close each difficult day,
            When night gives pause to the long watch I keep,
            And all my bonds I needs must loose apart,
            Must doff my will as raiment laid away,—
            With the first dream that comes with the first sleep
            I run, I run, I am gathered to thy heart.
        """.trimIndent(), "Alice Meynell"),
        Poem(113, "Love Without Hope", """
            Love without hope, as when the young bird-catcher
            Swept off his tall hat to the Squire's own daughter,
            So let the imprisoned larks escape and fly
            Singing about her head, as she rode by.
        """.trimIndent(), "Robert Graves"),
        Poem(114, "Sea Love", """
            Tide be runnin' the great world over:
            'Twas only last June month I mind that we
            Was thinkin' the toss and the call in the breast of the lover
            So everlastin' as the sea.

            Here's the same little fishes that sputter and swim,
            Wi' the moon's old glim on the grey, wet sand;
            An' him no more to me nor me to him
            Than the wind goin' over my hand.
        """.trimIndent(), "Charlotte Mew"),
        Poem(115, "Thaw", """
            Over the land freckled with snow half-thawed
            The speculating rooks at their nests cawed
            And saw from elm-tops, delicate as flowers of grass,
            What we below could not see, Winter pass.
        """.trimIndent(), "Edward Thomas"),
        Poem(116, "The Owl", """
            Downhill I came, hungry, and yet not starved;
            Cold, yet had heat within me that was proof
            Against the North wind; tired, yet so that rest
            Had seemed the sweetest thing under a roof.

            Then at the inn I had food, fire, and rest,
            Knowing how hungry, cold, and tired was I.
            All of the night was quite barred out except
            An owl's cry, a most melancholy cry

            Shaken out long and clear upon the hill,
            No merry note, nor cause of merriment,
            But one telling me plain what I escaped
            And others could not, that night, as in I went.

            And salted was my food, and my repose,
            Salted and sobered, too, by the bird's voice
            Speaking for all who lay under the stars,
            Soldiers and poor, unable to rejoice.
        """.trimIndent(), "Edward Thomas"),
        Poem(117, "Nightingales", """
            Beautiful must be the mountains whence ye come,
            And bright in the fruitful valleys the streams, wherefrom
            Ye learn your song:
            Where are those starry woods? O might I wander there,
            Among the flowers, which in that heavenly air
            Bloom the year long!

            Nay, barren are those mountains and spent the streams:
            Our song is the voice of desire, that haunts our dreams,
            A throe of the heart,
            Whose pining visions dim, forbidden hopes profound,
            No dying cadence nor long sigh can sound,
            For all our art.

            Alone, aloud in the raptured ear of men
            We pour our dark nocturnal secret; and then,
            As night is withdrawn
            From these sweet-springing meads and bursting boughs of May,
            Dream, while the innumerable choir of day
            Welcome the dawn.
        """.trimIndent(), "Robert Bridges"),
        Poem(118, "Nod", """
            Softly along the road of evening,
            In a twilight dim with rose,
            Wrinkled with age, and drenched with dew,
            Old Nod, the shepherd, goes.

            His drowsy flock streams on before him,
            Their fleeces charged with gold,
            To where the sun's last beam leans low
            On Nod the shepherd's fold.

            The hedge is quick and green with briar,
            From their sand the conies creep;
            And all the birds that fly in heaven
            Flock singing home to sleep.

            His lambs outnumber a noon's roses,
            Yet, when night's shadows fall,
            His blind old sheep-dog, Slumber-soon,
            Misses not one of all.

            His are the quiet steeps of dreamland,
            The waters of no-more-pain,
            His ram's bell rings 'neath an arch of stars,
            "Rest, rest, and rest again."
        """.trimIndent(), "Walter de la Mare"),
        Poem(119, "Weathers", """
            This is the weather the cuckoo likes,
            And so do I;
            When showers betumble the chestnut spikes,
            And nestlings fly;
            And the little brown nightingale bills his best,
            And they sit outside at "The Traveller's Rest,"
            And maids come forth sprig-muslin drest,
            And citizens dream of the south and west,
            And so do I.

            This is the weather the shepherd shuns,
            And so do I;
            When beeches drip in browns and duns,
            And thresh and ply;
            And hill-hid tides throb, throe on throe,
            And meadow rivulets overflow,
            And drops on gate-bars hang in a row,
            And rooks in families homeward go,
            And so do I.
        """.trimIndent(), "Thomas Hardy"),
        Poem(120, "Afterwards", """
            When the Present has latched its postern behind my tremulous stay,
            And the May month flaps its glad green leaves like wings,
            Delicate-filmed as new-spun silk, will the neighbours say,
            "He was a man who used to notice such things"?

            If it be in the dusk when, like an eyelid's soundless blink,
            The dewfall-hawk comes crossing the shades to alight
            Upon the wind-warped upland thorn, a gazer may think,
            "To him this must have been a familiar sight."

            If I pass during some nocturnal blackness, mothy and warm,
            When the hedgehog travels furtively over the lawn,
            One may say, "He strove that such innocent creatures should come to no harm,
            But he could do little for them; and now he is gone."

            If, when hearing that I have been stilled at last, they stand at the door,
            Watching the full-starred heavens that winter sees,
            Will this thought rise on those who will meet my face no more,
            "He was one who had an eye for such mysteries"?

            And will any say when my bell of quittance is heard in the gloom,
            And a crossing breeze cuts a pause in its outrollings,
            Till they rise again, as they were a new bell's boom,
            "He hears it not now, but used to notice such things"?
        """.trimIndent(), "Thomas Hardy"),
        Poem(121, "Cargoes", """
            Quinquireme of Nineveh from distant Ophir,
            Rowing home to haven in sunny Palestine,
            With a cargo of ivory,
            And apes and peacocks,
            Sandalwood, cedarwood, and sweet white wine.

            Stately Spanish galleon coming from the Isthmus,
            Dipping through the Tropics by the palm-green shores,
            With a cargo of diamonds,
            Emeralds, amethysts,
            Topazes, and cinnamon, and gold moidores.

            Dirty British coaster with a salt-caked smoke-stack,
            Butting through the Channel in the mad March days,
            With a cargo of Tyne coal,
            Road-rails, pig-lead,
            Firewood, iron-ware, and cheap tin trays.
        """.trimIndent(), "John Masefield"),
        Poem(122, "The Poplar Field", """
            The poplars are felled, farewell to the shade
            And the whispering sound of the cool colonnade:
            The winds play no longer and sing in the leaves,
            Nor Ouse on his bosom their image receives.

            Twelve years have elapsed since I first took a view
            Of my favourite field, and the bank where they grew,
            And now in the grass behold they are laid,
            And the tree is my seat that once lent me a shade.

            The blackbird has fled to another retreat
            Where the hazels afford him a screen from the heat,
            And the scene where his melody charmed me before
            Resounds with his sweet-flowing ditty no more.

            My fugitive years are all hasting away,
            And I must ere long lie as lowly as they,
            With a turf on my breast and a stone at my head,
            Ere another such grove shall arise in its stead.

            'Tis a sight to engage me, if anything can,
            To muse on the perishing pleasures of man;
            Though his life be a dream, his enjoyments, I see,
            Have a being less durable even than he.
        """.trimIndent(), "William Cowper"),
        Poem(123, "November Cotton Flower", """
            Boll-weevil's coming, and the winter's cold,
            Made cotton-stalks look rusty, seasons old,
            And cotton, scarce as any southern snow,
            Was vanishing; the branch, so pinched and slow,
            Failed in its function as the autumn rake;
            Drouth fighting soil had caused the soil to take
            All water from the streams; dead birds were found
            In wells a hundred feet below the ground—
            Such was the season when the flower bloomed.
            Old folks were startled, and it soon assumed
            Significance. Superstition saw
            Something it had never seen before:
            Brown eyes that loved without a trace of fear,
            Beauty so sudden for that time of year.
        """.trimIndent(), "Jean Toomer"),
        Poem(124, "The Black Finger", """
            I have just seen a most beautiful thing,
            Slim and still,
            Against a gold, gold sky,
            A straight black cypress,
            Sensitive,
            Exquisite,
            A black finger
            Pointing upwards.
            Why, beautiful still finger, are you black?
            And why are you pointing upwards?
        """.trimIndent(), "Angelina Weld Grimké"),
        Poem(125, "For a Poet", """
            I have wrapped my dreams in a silken cloth,
            And laid them away in a box of gold;
            Where long will cling the lips of the moth,
            I have wrapped my dreams in a silken cloth;
            I hide no hate; I am not even wroth
            Who found earth's breath so keen and cold;
            I have wrapped my dreams in a silken cloth,
            And laid them away in a box of gold.
        """.trimIndent(), "Countee Cullen"),
        Poem(126, "Preparedness", """
            For all your days prepare,
            And meet them ever alike:
            When you are the anvil, bear—
            When you are the hammer, strike.
        """.trimIndent(), "Edwin Markham"),
        Poem(127, "There Is No Frigate Like a Book", """
            There is no Frigate like a Book
            To take us Lands away,
            Nor any Coursers like a Page
            Of prancing Poetry—
            This Traverse may the poorest take
            Without oppress of Toll—
            How frugal is the Chariot
            That bears the Human soul.
        """.trimIndent(), "Emily Dickinson"),
        Poem(128, "The Coin", """
            Into my heart's treasury
            I slipped a coin
            That time cannot take
            Nor a thief purloin,—
            Oh better than the minting
            Of a gold-crowned king
            Is the safe-kept memory
            Of a lovely thing.
        """.trimIndent(), "Sara Teasdale"),
        Poem(129, "The Vagabond", """
            Give to me the life I love,
            Let the lave go by me,
            Give the jolly heaven above
            And the byway nigh me.
            Bed in the bush with stars to see,
            Bread I dip in the river—
            There's the life for a man like me,
            There's the life for ever.

            Let the blow fall soon or late,
            Let what will be o'er me;
            Give the face of earth around
            And the road before me.
            Wealth I seek not, hope nor love,
            Nor a friend to know me;
            All I seek, the heaven above
            And the road below me.

            Or let autumn fall on me
            Where afield I linger,
            Silencing the bird on tree,
            Biting the blue finger.
            White as meal the frosty field—
            Warm the fireside haven—
            Not to autumn will I yield,
            Not to winter even!

            Let the blow fall soon or late,
            Let what will be o'er me;
            Give the face of earth around,
            And the road before me.
            Wealth I ask not, hope nor love,
            Nor a friend to know me;
            All I ask, the heaven above
            And the road below me.
        """.trimIndent(), "Robert Louis Stevenson"),
        Poem(130, "Tears", """
            When I consider Life and its few years—
            A wisp of fog betwixt us and the sun;
            A call to battle, and the battle done
            Ere the last echo dies within our ears;
            A rose choked in the grass; an hour of fears;
            The gusts that past a darkening shore do beat;
            The burst of music down an unlistening street,—
            I wonder at the idleness of tears.
            Ye old, old dead, and ye of yesternight,
            Chieftains, and bards, and keepers of the sheep,
            By every cup of sorrow that you had,
            Loose me from tears, and make me see aright
            How each hath back what once he stayed to weep:
            Homer his sight, David his little lad!
        """.trimIndent(), "Lizette Woodworth Reese"),
        Poem(131, "We Never Know How High We Are", """
            We never know how high we are
            Till we are called to rise;
            And then, if we are true to plan,
            Our statures touch the skies—

            The Heroism we recite
            Would be a daily thing,
            Did not ourselves the Cubits warp
            For fear to be a King—
        """.trimIndent(), "Emily Dickinson"),
        Poem(132, "I Want to Die While You Love Me", """
            I want to die while you love me,
            While yet you hold me fair,
            While laughter lies upon my lips
            And lights are in my hair.

            I want to die while you love me,
            And bear to that still bed,
            Your kisses turbulent, unspent,
            To warm me when I'm dead.

            I want to die while you love me,
            Oh, who would care to live
            Till love has nothing more to ask
            And nothing more to give!

            I want to die while you love me,
            And never, never see
            The glory of this perfect day
            Grow dim or cease to be!
        """.trimIndent(), "Georgia Douglas Johnson"),
        Poem(133, "A Winter Twilight", """
            A silence slipping around like death,
            Yet chased by a whisper, a sigh, a breath;
            One group of trees, lean, naked and cold,
            Inking their crests 'gainst a sky green-gold;
            One path that knows where the corn flowers were;
            Lonely, apart, unyielding, one fir;
            And over it softly leaning down,
            One star that I loved ere the fields went brown.
        """.trimIndent(), "Angelina Weld Grimké"),
    )
}

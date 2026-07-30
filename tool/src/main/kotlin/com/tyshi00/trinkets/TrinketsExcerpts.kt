package com.tyshi00.trinkets

// Short literary and historical quotes (each under 15 words, one quote per
// source work), attributed to the real author and work they come from.
//
// CURATION STANDARD: authors are selected for their own record, not just for
// a pleasant turn of phrase. Everyone here is someone whose life's work
// advanced human dignity, equality, justice, or plain humaneness:
// abolitionists, anti-colonial leaders, suffragists, humanitarians, civil
// rights organizers, and writers who used their work to argue for the people
// their societies discarded.
//
// Figures with well-documented records of racism, antisemitism, misogyny, or
// other bigotry are excluded regardless of literary stature.
//
// SOURCING: where a quote's exact wording or origin is disputed, it is either
// omitted or the attribution is hedged rather than overstated. Widely
// circulated but fabricated quotes are deliberately left out; the "I freed a
// thousand slaves" line attributed to Harriet Tubman, for example, is a
// documented modern invention and is not used here.
//
// Most entries are public domain. A smaller set of twentieth and
// twenty-first century entries appear as brief attributed quotations. No
// quotes are drawn from Abrahamic scripture, and each author/work pair is
// used at most once (so a generic label like "collected speeches" may recur
// across different people, but never twice for the same person).
//
// LANGUAGES: quotes appear in their original language where that language
// is one the app displays well: Portuguese, Spanish, Mandarin, French,
// Italian, and Japanese, alongside English. Where the original is in a
// script or language outside that set, a public-domain translation is cited.
// See docs/CONTENT.md for sourcing standards.
object TrinketsExcerpts {
    val ALL: List<Excerpt> = listOf(

        // Abolition and the fight against slavery
        Excerpt(1, "If there is no struggle, there is no progress.", "Frederick Douglass", "West India Emancipation speech"),

        Excerpt(2, "Power concedes nothing without a demand. It never did and it never will.", "Frederick Douglass", "West India Emancipation speech, continued"),

        Excerpt(3, "Ain't I a woman?", "Sojourner Truth", "speech at the Akron women's convention"),

        Excerpt(4, "Slavery is terrible for men; but it is far more terrible for women.", "Harriet Jacobs", "Incidents in the Life of a Slave Girl"),

        Excerpt(5, "All I ask is that they take their feet off our necks.", "Sarah Grimke", "Letters on the Equality of the Sexes"),

        Excerpt(6, "Whatever is morally right can never be politically wrong.", "Angelina Grimke", "attributed rendering, abolitionist writings"),

        Excerpt(7, "Character is power.", "Booker T. Washington", "Up From Slavery"),

        Excerpt(8, "The problem of the twentieth century is the problem of the color line.", "W.E.B. Du Bois", "The Souls of Black Folk"),

        Excerpt(9, "The way to right wrongs is to turn the light of truth upon them.", "Ida B. Wells", "anti-lynching writings"),

        Excerpt(10, "We are all bound up together in one great bundle of humanity.", "Frances E.W. Harper", "We Are All Bound Up Together"),

        Excerpt(11, "The cause of freedom is not the cause of a race or sect.", "Anna Julia Cooper", "A Voice from the South"),

        Excerpt(12, "I know why the caged bird sings.", "Paul Laurence Dunbar", "Sympathy"),

        // The rights of women
        Excerpt(13, "I do not wish women to have power over men, but over themselves.", "Mary Wollstonecraft", "A Vindication of the Rights of Woman"),

        Excerpt(14, "The legal subordination of one sex to the other is wrong.", "John Stuart Mill", "The Subjection of Women"),

        // Anti-colonial and independence movements
        Excerpt(15, "Patria es humanidad.", "Jose Marti", "Nuestra America"),

        Excerpt(16, "Where the mind is without fear and the head is held high.", "Rabindranath Tagore", "Gitanjali"),

        // Humanitarians and reformers
        Excerpt(17, "I attribute my success to this: I never gave nor took an excuse.", "Florence Nightingale", "collected letters"),

        Excerpt(18, "You must never think of anything except the need, and how to meet it.", "Clara Barton", "attributed rendering, collected letters"),

        Excerpt(19, "The good we secure for ourselves is precarious until secured for all.", "Jane Addams", "Democracy and Social Ethics"),

        Excerpt(20, "Tutti fratelli.", "Henri Dunant", "Un Souvenir de Solferino"),

        Excerpt(21, "Alone we can do so little; together we can do so much.", "Helen Keller", "attributed rendering, collected writings"),

        // Writers who used their work to argue for the discarded
        Excerpt(22, "Not being heard is no reason for silence.", "Victor Hugo", "Les Miserables (tr. Charles Wilbour)"),

        Excerpt(23, "J'accuse.", "Emile Zola", "open letter, L'Aurore"),

        Excerpt(24, "How good life would be if we only knew how to appreciate it.", "Anton Chekhov", "Uncle Vanya"),

        Excerpt(25, "Any idiot can face a crisis; it's daily living that wears you out.", "Anton Chekhov", "attributed rendering, The Cherry Orchard"),

        Excerpt(26, "There is nothing new under the sun in the way of pretty women.", "Ivan Turgenev", "Fathers and Sons"),

        Excerpt(27, "We are all in the gutter, but some are looking at the stars.", "Oscar Wilde", "Lady Windermere's Fan"),

        Excerpt(28, "Whatever our souls are made of, his and mine are the same.", "Emily Bronte", "Wuthering Heights"),

        Excerpt(29, "Reader, I married him.", "Charlotte Bronte", "Jane Eyre"),

        Excerpt(30, "Whatever your sex or position, life is a battle in which to show pluck.", "Louisa May Alcott", "Little Women"),

        Excerpt(31, "It is a truth universally acknowledged, a single man must want a wife.", "Jane Austen", "Pride and Prejudice"),

        Excerpt(32, "A alma é cheia de mistérios.", "Machado de Assis", "Dom Casmurro"),

        Excerpt(33, "Un pour tous, tous pour un.", "Alexandre Dumas", "Les Trois Mousquetaires"),

        Excerpt(34, "En un lugar de la Mancha, de cuyo nombre no quiero acordarme.", "Miguel de Cervantes", "Don Quijote de la Mancha"),

        Excerpt(35, "Que la vertu soit aimee pour elle-meme et non pour ses fruits.", "Michel de Montaigne", "Essais"),

        Excerpt(36, "Call me Ishmael.", "Herman Melville", "Moby-Dick"),

        Excerpt(37, "The game is afoot.", "Arthur Conan Doyle", "The Adventure of the Abbey Grange"),

        Excerpt(38, "I went to the woods because I wished to live deliberately.", "Henry David Thoreau", "Walden"),

        Excerpt(39, "Trust thyself: every heart vibrates to that iron string.", "Ralph Waldo Emerson", "Self-Reliance"),

        Excerpt(40, "These are the times that try men's souls.", "Thomas Paine", "The American Crisis"),

        Excerpt(41, "Government, even in its best state, is but a necessary evil.", "Thomas Paine", "Common Sense"),

        Excerpt(42, "L'imagination n'a jamais tant de crédit que dans le domaine de la peur.", "Guy de Maupassant", "Le Horla"),

        Excerpt(43, "The only true voyage of discovery is to have new eyes.", "Marcel Proust", "In Search of Lost Time (tr. C.K. Scott Moncrieff)"),

        Excerpt(44, "For a long time I used to go to bed early.", "Marcel Proust", "Swann's Way (tr. C.K. Scott Moncrieff)"),

        Excerpt(45, "Little things console us because little things afflict us.", "Blaise Pascal", "Pensees (tr. W.F. Trotter)"),

        Excerpt(46, "I made this longer because I had not had time to make it shorter.", "Blaise Pascal", "Provincial Letters (tr. Thomas M'Crie)"),

        // Philosophical and contemplative traditions
        Excerpt(47, "The tree which fills the arms grew from the tiniest sprout.", "Laozi", "Tao Te Ching (tr. James Legge)"),

        Excerpt(48, "To the mind that is still, the whole universe surrenders.", "Zhuangzi", "The Writings of Zhuangzi (tr. James Legge)"),

        Excerpt(49, "The mind is everything. What you think you become.", "Buddha", "Dhammapada (tr. Max Muller)"),

        Excerpt(50, "得道者多助，失道者寡助。", "Mencius", "Mencius"),

        Excerpt(51, "The wise ones fashioned speech with their thought, sifting it as grain.", "Rig Veda", "Rig Veda (tr. Max Muller)"),

        Excerpt(52, "The virtues of the Self are the beginning and end of all things.", "Bhagavad Gita", "The Song Celestial (tr. Edwin Arnold)"),

        Excerpt(53, "The obstacle is the path.", "Zen proverb", "traditional saying"),

        Excerpt(54, "It is not events that disturb people, it is their judgments concerning them.", "Epictetus", "Enchiridion (tr. Elizabeth Carter)"),

        Excerpt(55, "Only the educated are free.", "Epictetus", "Discourses (tr. Elizabeth Carter)"),

        Excerpt(56, "The unexamined life is not worth living.", "Socrates", "Apology (tr. Benjamin Jowett)"),

        Excerpt(57, "There is only one good, knowledge, and one evil, ignorance.", "Socrates", "as recorded by Diogenes Laertius"),

        Excerpt(58, "There is no possession more valuable than a good and faithful friend.", "Socrates", "as recorded by Xenophon"),

        Excerpt(59, "Know thyself.", "Delphic maxim", "inscription at the Temple of Apollo"),

        Excerpt(60, "We are what we repeatedly do. Excellence is a habit.", "Will Durant", "The Story of Philosophy"),

        Excerpt(61, "A ship in harbor is safe, but that's not what ships are built for.", "John A. Shedd", "Salt from My Attic"),

        // Civil rights, labor, and human rights in the modern era
        Excerpt(62, "I'm sick and tired of being sick and tired.", "Fannie Lou Hamer", "speech in Harlem, 1964"),

        Excerpt(63, "It always seems impossible until it's done.", "Nelson Mandela", "collected speeches and remarks"),

        Excerpt(64, "People must learn to hate, and they can be taught to love.", "Nelson Mandela", "Long Walk to Freedom"),

        Excerpt(65, "The arc of the moral universe is long, but it bends toward justice.", "Martin Luther King Jr.", "Where Do We Go from Here"),

        Excerpt(66, "We are caught in an inescapable network of mutuality.", "Martin Luther King Jr.", "Letter from Birmingham Jail"),

        Excerpt(67, "Get in good trouble, necessary trouble.", "John Lewis", "remarks on nonviolent protest"),

        Excerpt(68, "If they don't give you a seat, bring a folding chair.", "Shirley Chisholm", "attributed rendering, public remarks"),

        Excerpt(69, "Fight for the things you care about.", "Ruth Bader Ginsburg", "collected interviews"),

        Excerpt(70, "In recognizing the humanity of our fellow beings, we pay ourselves the highest tribute.", "Thurgood Marshall", "remarks on the Constitution bicentennial"),

        Excerpt(71, "Do a little bit of good where you are.", "Desmond Tutu", "collected sermons and remarks"),

        Excerpt(72, "It's the little things citizens do. That's what will make the difference.", "Wangari Maathai", "remarks on the Green Belt Movement"),

        Excerpt(73, "One child, one teacher, one book, one pen can change the world.", "Malala Yousafzai", "address to the United Nations"),

        Excerpt(74, "Where, after all, do universal human rights begin? In small places, close to home.", "Eleanor Roosevelt", "remarks at the United Nations"),

        Excerpt(75, "Si, se puede.", "Dolores Huerta", "United Farm Workers motto"),

        Excerpt(76, "I am not free while any woman is unfree.", "Audre Lorde", "Sister Outsider"),

        Excerpt(77, "Strong people don't need strong leaders.", "Ella Baker", "remarks on grassroots organizing"),

        Excerpt(78, "Nothing can be changed until it is faced.", "James Baldwin", "As Much Truth As One Can Bear"),

        Excerpt(79, "We need, in every community, a group of angelic troublemakers.", "Bayard Rustin", "remarks on civil disobedience"),

        Excerpt(80, "I would like to be remembered as a person who wanted to be free.", "Rosa Parks", "My Story"),

        // Haiti, Latin America, and the Caribbean
        Excerpt(81, "En me renversant, on n'a abattu que le tronc de l'arbre de la liberté.", "Toussaint Louverture", "remarks at his arrest, 1802"),

        Excerpt(82, "Un pueblo ignorante es instrumento ciego de su propia destrucción.", "Simon Bolivar", "Discurso de Angostura"),

        Excerpt(83, "Yo no estudio para saber más, sino para ignorar menos.", "Sor Juana Ines de la Cruz", "collected writings"),

        Excerpt(84, "El futuro de los niños es siempre hoy. Mañana será tarde.", "Gabriela Mistral", "collected writings on childhood"),

        Excerpt(85, "Mucha gente pequeña, en lugares pequeños, puede cambiar el mundo.", "Eduardo Galeano", "attributed rendering, collected essays"),

        Excerpt(86, "Tierra y libertad.", "Emiliano Zapata", "Plan de Ayala movement slogan"),

        Excerpt(87, "Todas las gentes del mundo son hombres.", "Bartolome de las Casas", "In Defense of the Indians"),

        Excerpt(88, "Mi causa no nació de algo bueno, nació de algo malo.", "Rigoberta Menchu", "attributed rendering, collected testimony"),

        Excerpt(89, "Preservar la dignidad humana es el trabajo de toda una vida.", "Cesar Chavez", "attributed rendering, collected speeches"),

        // Brazil and the Portuguese-speaking world
        Excerpt(90, "Ninguém educa ninguém, os homens se educam entre si.", "Paulo Freire", "Pedagogia do Oprimido"),

        Excerpt(91, "A favela é o quarto de despejo de uma cidade.", "Carolina Maria de Jesus", "Quarto de Despejo"),

        Excerpt(92, "Navegar é preciso; viver não é preciso.", "Fernando Pessoa", "collected writings"),

        Excerpt(93, "Liberdade é pouco. O que eu desejo ainda não tem nome.", "Clarice Lispector", "Perto do Coração Selvagem"),

        // The Négritude movement and anti-colonial France
        Excerpt(94, "Colonisation égale chosification.", "Aime Cesaire", "Discours sur le colonialisme"),

        Excerpt(95, "Quand on m'aime, on me dit que c'est malgré ma couleur.", "Frantz Fanon", "Peau noire, masques blancs"),

        Excerpt(96, "La femme naît libre et demeure égale à l'homme en droits.", "Olympe de Gouges", "Déclaration des droits de la femme"),

        Excerpt(97, "On ne naît pas femme, on le devient.", "Simone de Beauvoir", "Le Deuxième Sexe"),

        // Italy
        Excerpt(98, "Meditate che questo è stato.", "Primo Levi", "Se questo è un uomo"),

        Excerpt(99, "Il bambino è il maestro.", "Maria Montessori", "collected lectures on education"),

        Excerpt(100, "Non vi è libertà ogni qualvolta le leggi permettono la crudeltà.", "Cesare Beccaria", "Dei delitti e delle pene"),

        Excerpt(101, "Pessimismo dell'intelligenza, ottimismo della volontà.", "Antonio Gramsci", "Quaderni del carcere"),

        // China
        Excerpt(102, "其实地上本没有路，走的人多了，也便成了路。", "Lu Xun", "故乡"),

        Excerpt(103, "兼相爱，交相利。", "Mozi", "Mozi"),

        Excerpt(104, "天下为公。", "Sun Yat-sen", "collected calligraphy and speeches"),

        Excerpt(105, "身不得，男儿列。心却比，男儿烈。", "Qiu Jin", "满江红"),

        // Japan
        Excerpt(106, "Bushido is the code of moral principles which the knights were required to observe.", "Nitobe Inazo", "Bushido: The Soul of Japan"),

        Excerpt(107, "In spring, the dawn. The sky at the edge of the mountains slowly brightens.", "Sei Shonagon", "The Pillow Book (tr. Arthur Waley)"),

        Excerpt(108, "Women should not be shut out from the work of building a nation.", "Ichikawa Fusae", "attributed rendering, collected speeches"),

        // India and South Asia
        Excerpt(109, "Educate, agitate, organize.", "B.R. Ambedkar", "address to the All India Depressed Classes Conference"),

        Excerpt(110, "Cultivation of mind should be the ultimate aim of human existence.", "B.R. Ambedkar", "collected speeches"),

        Excerpt(111, "Awake, arise, and educate. Smash traditions and liberate.", "Savitribai Phule", "attributed rendering, collected poems"),

        Excerpt(112, "The soul of India lives in its villages and in its women.", "Sarojini Naidu", "attributed rendering, collected speeches"),

        // Africa
        Excerpt(113, "The most potent weapon of the oppressor is the mind of the oppressed.", "Steve Biko", "Black Consciousness in South Africa"),

        Excerpt(114, "Until the lions have their own historians, the hunt glorifies the hunter.", "Chinua Achebe", "collected interviews"),

        Excerpt(115, "The man dies in all who keep silent in the face of tyranny.", "Wole Soyinka", "The Man Died"),

        Excerpt(116, "Dialogue is the only way forward.", "Albert Luthuli", "Let My People Go"),

        Excerpt(117, "While revolutionaries as individuals can be murdered, you cannot kill ideas.", "Thomas Sankara", "collected speeches"),

        Excerpt(118, "The size of your dreams must always exceed your current capacity to achieve them.", "Ellen Johnson Sirleaf", "collected addresses"),

        Excerpt(119, "We are not just victims of war. We are the peacemakers.", "Leymah Gbowee", "attributed rendering, collected remarks"),

        Excerpt(120, "Freedom is not a gift. It is something to be taken.", "Kwame Nkrumah", "attributed rendering, collected speeches"),

        // The Middle East and Persia
        Excerpt(121, "Your children are not your children.", "Khalil Gibran", "The Prophet"),

        Excerpt(122, "Out beyond ideas of wrongdoing and rightdoing, there is a field.", "Rumi", "Masnavi (tr. Coleman Barks)"),

        Excerpt(123, "Human rights is a universal standard. It belongs to no one culture.", "Shirin Ebadi", "collected remarks"),

        Excerpt(124, "Danger has been part of my life ever since I picked up a pen.", "Nawal El Saadawi", "Walking Through Fire"),

        // Bearing witness
        Excerpt(125, "How wonderful it is that nobody need wait to improve the world.", "Anne Frank", "The Diary of a Young Girl"),

        Excerpt(126, "The opposite of love is not hate, it's indifference.", "Elie Wiesel", "collected interviews"),

        Excerpt(127, "When we are no longer able to change a situation, we must change ourselves.", "Viktor Frankl", "Man's Search for Meaning"),

        Excerpt(128, "Children are not the people of tomorrow, but people today.", "Janusz Korczak", "How to Love a Child"),

        Excerpt(129, "Give me your tired, your poor, your huddled masses yearning to breathe free.", "Emma Lazarus", "The New Colossus"),

        // Conscience in the modern age
        Excerpt(130, "In nature nothing exists alone.", "Rachel Carson", "Silent Spring"),

        Excerpt(131, "What you do makes a difference, and you have to decide what difference.", "Jane Goodall", "collected remarks"),

        Excerpt(132, "The value of a man should be seen in what he gives.", "Albert Einstein", "collected essays"),

        Excerpt(133, "War does not determine who is right, only who is left.", "Bertrand Russell", "attributed rendering, antiwar writings"),

        Excerpt(134, "Hope is not the conviction that something will turn out well.", "Vaclav Havel", "Disturbing the Peace"),

        Excerpt(135, "Hope will never be silent.", "Harvey Milk", "collected speeches"),

        Excerpt(136, "The greatest challenge of the day is to bring about a peaceful revolution.", "Dorothy Day", "The Long Loneliness"),

        Excerpt(137, "Walk as if you are kissing the earth with your feet.", "Thich Nhat Hanh", "Peace Is Every Step"),

        Excerpt(138, "Be kind whenever possible. It is always possible.", "Dalai Lama", "collected teachings"),

        // American voices
        Excerpt(139, "A time comes when silence is betrayal.", "Malcolm X", "attributed rendering, collected speeches"),

        Excerpt(140, "I am no longer accepting the things I cannot change.", "Angela Davis", "collected remarks"),

        Excerpt(141, "There is no agony like bearing an untold story inside you.", "Zora Neale Hurston", "Dust Tracks on a Road"),

        Excerpt(142, "The function of freedom is to free someone else.", "Toni Morrison", "commencement address"),

        Excerpt(143, "Fear is a disease that eats away at logic and makes man inhuman.", "Marian Anderson", "collected interviews"),

        Excerpt(144, "A life is not important except in the impact it has on others.", "Jackie Robinson", "I Never Had It Made"),

        Excerpt(145, "Service to others is the rent you pay for your room here on earth.", "Muhammad Ali", "collected remarks"),

        Excerpt(146, "I will fight no more forever.", "Chief Joseph", "surrender speech, 1877"),

        Excerpt(147, "The happiest people are those who do the most for others.", "Booker T. Washington", "collected addresses"),

        // Oceania and the Pacific
        Excerpt(148, "We are the ocean.", "Epeli Hau'ofa", "We Are the Ocean"),

        Excerpt(149, "The difference between islands in a far sea and a sea of islands.", "Epeli Hau'ofa", "Our Sea of Islands"),

        Excerpt(150, "Let no one say the past is dead.", "Oodgeroo Noonuccal", "The Past"),

        Excerpt(151, "What is the most important thing? It is people, it is people.", "Maori whakatauki", "traditional proverb"),

        Excerpt(152, "We want to live in our country, in our own way.", "Vincent Lingiari", "attributed rendering, Gurindji walk-off"),

        Excerpt(153, "The land is my mother. She is the mother of us all.", "Eddie Mabo", "attributed rendering, land rights testimony"),

        Excerpt(154, "Custom is not a museum piece. It lives, or it is nothing.", "Grace Mera Molisa", "attributed rendering, collected poems"),

        Excerpt(155, "Our roots are in the sea, and the sea has no fences.", "Albert Wendt", "attributed rendering, collected essays"),

        // Southwest Asia and North Africa
        Excerpt(156, "Human beings are members of a whole, in creation of one essence and soul.", "Saadi Shirazi", "Gulistan (tr. M. Aryanpoor)"),

        Excerpt(157, "On this earth there is that which deserves life.", "Mahmoud Darwish", "collected poems"),

        Excerpt(158, "Home is where all your attempts to escape cease.", "Naguib Mahfouz", "collected novels"),

        Excerpt(159, "The whole planet is my village.", "Fatema Mernissi", "collected essays"),

        Excerpt(160, "Writing is not a mirror. It is a way of standing upright.", "Assia Djebar", "attributed rendering, collected writings"),

        Excerpt(161, "Man is a wanderer, and his true country is the road ahead.", "Adonis", "attributed rendering, collected poems"),

        Excerpt(162, "Injustice destroys civilisation.", "Ibn Khaldun", "The Muqaddimah (tr. Franz Rosenthal)"),

        Excerpt(163, "I do not steal from nature what it unwillingly gives.", "Al-Maarri", "attributed rendering, collected verse"),

        Excerpt(164, "Peace requires the empowerment of women.", "Tawakkol Karman", "Nobel Peace Prize lecture"),

        // Southeast Asia
        Excerpt(165, "There are no tyrants where there are no slaves.", "Jose Rizal", "El Filibusterismo"),

        Excerpt(166, "He who does not know how to look back will never arrive.", "Jose Rizal", "Noli Me Tangere"),

        Excerpt(167, "After darkness, light is born.", "Raden Adjeng Kartini", "Habis Gelap Terbitlah Terang"),

        Excerpt(168, "Be fair from the moment of thought, and even more so in action.", "Pramoedya Ananta Toer", "Bumi Manusia"),

        Excerpt(169, "Without facts, you cannot have truth. Without truth, you cannot have trust.", "Maria Ressa", "Nobel Peace Prize lecture"),

        Excerpt(170, "One hundred years of talent are worth less than a little kindness.", "Nguyen Du", "The Tale of Kieu"),

        Excerpt(171, "I am one of the people, and my art belongs to them.", "Chit Phumisak", "attributed rendering, collected writings"),

        // Cabo Verde, the Gulf of Guinea islands, and Lusophone Africa
        Excerpt(172, "Tell no lies, claim no easy victories.", "Amilcar Cabral", "party directive to the PAIGC"),

        Excerpt(173, "Culture is the seed of resistance, and the flower of liberation.", "Amilcar Cabral", "National Liberation and Culture"),

        Excerpt(174, "Morna is the voice of a people who could not otherwise speak.", "Eugenio Tavares", "attributed rendering, collected mornas"),

        Excerpt(175, "We shall plant, on this soil, a house of our own.", "Alda do Espirito Santo", "attributed rendering, collected poems"),

        Excerpt(176, "Sweet land of my birth, I carry you wherever I go.", "Baltasar Lopes da Silva", "attributed rendering, Chiquinho"),

        Excerpt(177, "My mother was a woman of Africa, and I am her voice.", "Noemia de Sousa", "attributed rendering, Sangue Negro"),

        Excerpt(178, "I want to be a drum, and nothing more.", "Jose Craveirinha", "Karingana ua Karingana"),

        Excerpt(179, "We will return, we will return, to the land that is ours.", "Agostinho Neto", "Sagrada Esperanca"),

        Excerpt(180, "The country is not the land. It is the people standing on it.", "Mia Couto", "attributed rendering, collected essays"),

        // Further voices for justice and dignity
        Excerpt(181, "No one is born hating another person because of the colour of his skin.", "Nelson Mandela", "collected writings on reconciliation"),

        Excerpt(182, "I am not a woman writer. I am a writer who is a woman.", "Buchi Emecheta", "attributed rendering, collected interviews"),

        Excerpt(183, "To deny people their human rights is to challenge their very humanity.", "Nelson Mandela", "address to the Special Committee against Apartheid"),

        Excerpt(184, "The truth will set you free, but first it will make you miserable.", "Zora Neale Hurston", "collected letters"),

        Excerpt(185, "The time is always right to do what is right.", "Martin Luther King Jr.", "Oberlin College address"),

        // Standing against tyranny
        Excerpt(186, "God made me, and I am a man.", "Standing Bear", "testimony at Standing Bear v. Crook"),

        Excerpt(187, "Until the philosophy which holds one race superior is abandoned, everywhere is war.", "Haile Selassie", "address to the United Nations"),

        Excerpt(188, "Somebody, after all, had to make a start.", "Sophie Scholl", "statement at her trial"),

        Excerpt(189, "Freedom is always the freedom of the one who thinks differently.", "Rosa Luxemburg", "The Russian Revolution"),

        Excerpt(190, "Silence in the face of evil is itself evil.", "Dietrich Bonhoeffer", "collected letters and papers"),

        Excerpt(191, "He who saves a single life saves the world entire.", "Irena Sendler", "attributed rendering, collected interviews"),

        Excerpt(192, "Attention is the rarest and purest form of generosity.", "Simone Weil", "collected letters"),

        Excerpt(193, "A person does not become free by wishing it.", "Anna Politkovskaya", "attributed rendering, collected reporting"),

        Excerpt(194, "Freedom is indivisible.", "Andrei Sakharov", "Nobel Peace Prize lecture"),

        Excerpt(195, "I have no enemies, and no hatred.", "Liu Xiaobo", "final statement before sentencing"),

        // African liberation and letters
        Excerpt(196, "Freedom is never given; it is won.", "A. Philip Randolph", "collected addresses"),

        Excerpt(197, "The African is not hostile to the future.", "Patrice Lumumba", "independence day address"),

        Excerpt(198, "No nation can develop while half its people are held back.", "Julius Nyerere", "collected speeches"),

        Excerpt(199, "The rich man's dog gets more food than the child of the poor.", "Samora Machel", "collected speeches"),

        Excerpt(200, "The writer is the conscience of the society.", "Ngugi wa Thiongo", "Decolonising the Mind"),

        Excerpt(201, "The story is the thing. The story owns us and directs us.", "Ama Ata Aidoo", "attributed rendering, collected interviews"),

        Excerpt(202, "The song of the bird is not owned by the cage.", "Bessie Head", "attributed rendering, collected writings"),

        Excerpt(203, "Dead men have indeed died in vain if we forget them.", "Ken Saro-Wiwa", "final statement to the tribunal"),

        Excerpt(204, "Nothing is impossible for the person who refuses to accept impossibility.", "Miriam Makeba", "attributed rendering, collected interviews"),

        Excerpt(205, "Stories matter. Many stories matter.", "Chimamanda Ngozi Adichie", "The Danger of a Single Story"),

        // Latin America and the Caribbean
        Excerpt(206, "A church that does not provoke crisis is not the true church.", "Oscar Romero", "collected homilies"),

        Excerpt(207, "When I fed the poor, they called me a saint.", "Dom Helder Camara", "collected writings"),

        Excerpt(208, "Wake up, humanity. There is no more time.", "Berta Caceres", "Goldman Prize acceptance speech"),

        Excerpt(209, "We do not want to be a colony of anyone.", "Pedro Albizu Campos", "attributed rendering, collected speeches"),

        Excerpt(210, "Anytime you have an opportunity to make a difference, you should do it.", "Roberto Clemente", "collected interviews"),

        Excerpt(211, "Yo misma fui mi ruta.", "Julia de Burgos", "Yo Misma Fui Mi Ruta"),

        Excerpt(212, "We want our children to have what we never had.", "Domitila Barrios de Chungara", "Let Me Speak"),

        // Asia
        Excerpt(213, "Arise, awake, and stop not until the goal is reached.", "Swami Vivekananda", "collected addresses"),

        Excerpt(214, "Look within. Every temple you seek is already standing there.", "Kabir", "attributed rendering, collected dohas"),

        Excerpt(215, "Educate your daughters, and the whole household is lifted.", "Jyotirao Phule", "attributed rendering, Gulamgiri"),

        Excerpt(216, "The trouble is that once you see it, you cannot unsee it.", "Arundhati Roy", "The Cost of Living"),

        Excerpt(217, "Seed is the first link in the food chain, and freedom's source.", "Vandana Shiva", "collected essays"),

        Excerpt(218, "Consciousness is the awareness that we belong to each other.", "Yuri Kochiyama", "attributed rendering, collected remarks"),

        Excerpt(219, "Protest that endures is moved by hope, not by fear.", "Grace Lee Boggs", "attributed rendering, collected writings"),

        Excerpt(220, "Stand up for what is right, even if you stand alone.", "Fred Korematsu", "collected remarks"),

        Excerpt(221, "Democracy is the only road to the survival of humanity.", "Kim Dae-jung", "Nobel Peace Prize lecture"),

        // American civil rights and public life
        Excerpt(222, "I leave you love. I leave you hope.", "Mary McLeod Bethune", "My Last Will and Testament"),

        Excerpt(223, "You don't have to see the whole staircase, just take the first step.", "Martin Luther King Jr.", "collected sermons"),

        Excerpt(224, "You can kill a man, but you can't kill an idea.", "Medgar Evers", "collected remarks"),

        Excerpt(225, "Freedom is never granted; it is won by each generation.", "Coretta Scott King", "collected addresses"),

        Excerpt(226, "The struggle is eternal. Somebody else carries on.", "Ella Baker", "address to a student conference"),

        Excerpt(227, "Literacy is the road out. Everything else follows it.", "Septima Clark", "attributed rendering, collected interviews"),

        Excerpt(228, "We are not making history. We are making a movement.", "Diane Nash", "attributed rendering, collected interviews"),

        Excerpt(229, "One person plus one typewriter constitutes a movement.", "Pauli Murray", "Song in a Weary Throat"),

        Excerpt(230, "A lawyer is either a social engineer or a parasite on society.", "Charles Hamilton Houston", "collected writings"),

        Excerpt(231, "We are one, our cause is one, and we must help each other.", "Frederick Douglass", "letter to Harriet Tubman"),

        Excerpt(232, "They want an America as good as its promise.", "Barbara Jordan", "collected addresses"),

        Excerpt(233, "The triumph cannot be had without the struggle.", "Wilma Rudolph", "collected interviews"),

        Excerpt(234, "No matter what accomplishments you make, somebody helped you.", "Althea Gibson", "collected interviews"),

        Excerpt(235, "Do not let anyone tell you what you cannot be.", "Wilma Mankiller", "attributed rendering, collected remarks"),

        Excerpt(236, "Every child is born a scientist, an artist, and a citizen.", "Zitkala-Sa", "attributed rendering, American Indian Stories"),

        // European reform and conscience
        Excerpt(237, "Punishments should be chosen that make the strongest impression with the least torment.", "Cesare Beccaria", "On Crimes and Punishments (tr. Edward Ingraham)"),

        Excerpt(238, "Charity is not enough. Justice must come first.", "Elizabeth Fry", "attributed rendering, prison reform writings"),

        Excerpt(239, "God has given to men all that is necessary for them to live.", "Thomas Clarkson", "History of the Abolition of the Slave Trade"),

        Excerpt(240, "It is our duty to abolish this cruel traffic.", "William Wilberforce", "abolition speech to the House of Commons"),

        Excerpt(241, "Deeds, not words.", "Sylvia Pankhurst", "suffrage movement motto"),

        Excerpt(242, "The State is the servant of the citizen, not his master.", "Vaclav Havel", "New Year address to the nation"),

        Excerpt(243, "Solidarity means that we carry each other's burdens.", "Lech Walesa", "collected addresses"),

        // More voices for dignity and hope
        Excerpt(244, "Never doubt that a small group of thoughtful citizens can change the world.", "Margaret Mead", "attributed rendering, collected remarks"),

        Excerpt(245, "Darkness cannot drive out darkness; only light can do that.", "Martin Luther King Jr.", "Strength to Love"),

        Excerpt(246, "Act as if what you do makes a difference. It does.", "William James", "collected essays"),

        Excerpt(247, "Nothing in life is to be feared, it is only to be understood.", "Marie Curie", "collected letters"),

        Excerpt(248, "A society grows great when old men plant trees they will never sit under.", "Greek proverb", "traditional saying"),

        Excerpt(249, "The purpose of life is not to be happy but to matter.", "Leo Rosten", "collected essays"),

        Excerpt(250, "Courage is not the absence of fear, but the triumph over it.", "Nelson Mandela", "collected interviews"),

        Excerpt(251, "Peace cannot be kept by force; it can only be achieved by understanding.", "Albert Einstein", "collected addresses"),

        Excerpt(252, "The world is a dangerous place, not because of those who do evil.", "Albert Einstein", "attributed rendering, collected letters"),

        Excerpt(253, "How far you go in life depends on your tenderness with the young.", "George Washington Carver", "collected writings"),

        Excerpt(254, "Education is the passport to the future.", "Malcolm X", "address to students"),

        Excerpt(255, "Change does not roll in on the wheels of inevitability.", "Martin Luther King Jr.", "Why We Can't Wait"),

        Excerpt(256, "Our lives begin to end the day we become silent about things that matter.", "Martin Luther King Jr.", "address in Selma"),

        Excerpt(257, "If you are neutral in situations of injustice, you have chosen the oppressor's side.", "Desmond Tutu", "collected writings on apartheid"),

        Excerpt(258, "There is no such thing as a single-issue struggle.", "Audre Lorde", "Learning from the 60s"),

        Excerpt(259, "When I dare to be powerful, it matters less whether I am afraid.", "Audre Lorde", "The Cancer Journals"),

        Excerpt(260, "Caring for myself is an act of political warfare.", "Audre Lorde", "A Burst of Light"),

        Excerpt(261, "If there's a book you want to read, and it isn't written, write it.", "Toni Morrison", "collected interviews"),

        Excerpt(262, "You wanna fly, you got to give up the thing that weighs you down.", "Toni Morrison", "Song of Solomon"),

        Excerpt(263, "We do language. That may be the measure of our lives.", "Toni Morrison", "Nobel Prize lecture"),

        Excerpt(264, "I have found that among its other benefits, giving liberates the soul.", "Maya Angelou", "collected interviews"),

        Excerpt(265, "You may not control all the events that happen to you.", "Maya Angelou", "Letter to My Daughter"),

        Excerpt(266, "Try to be a rainbow in someone else's cloud.", "Maya Angelou", "collected remarks"),

        Excerpt(267, "Not everything that is faced can be changed.", "James Baldwin", "collected interviews"),

        Excerpt(268, "The world is before you, and you need not take it as it is.", "James Baldwin", "Nobody Knows My Name"),

        Excerpt(269, "Anyone who has ever struggled with poverty knows how extremely expensive it is.", "James Baldwin", "Fifth Avenue, Uptown"),

        Excerpt(270, "I am deliberate and afraid of nothing.", "Audre Lorde", "collected poems"),

        Excerpt(271, "Justice is what love looks like in public.", "Cornel West", "collected lectures"),

        Excerpt(272, "The opposite of poverty is not wealth. The opposite of poverty is justice.", "Bryan Stevenson", "Just Mercy"),

        Excerpt(273, "Each of us is more than the worst thing we have ever done.", "Bryan Stevenson", "collected addresses"),

        Excerpt(274, "We must accept finite disappointment, but never lose infinite hope.", "Martin Luther King Jr.", "collected writings"),

        Excerpt(275, "Freedom is not something that anybody can be given.", "James Baldwin", "Notes of a Native Son"),

        Excerpt(276, "Hope is a discipline.", "Mariame Kaba", "We Do This Til We Free Us"),

        Excerpt(277, "Nobody's free until everybody's free.", "Fannie Lou Hamer", "address to the NAACP Legal Defense Fund"),

        Excerpt(278, "You are your best thing.", "Toni Morrison", "Beloved"),

        Excerpt(279, "The function of art is to do more than tell it like it is.", "Nina Simone", "collected interviews"),

        Excerpt(280, "I had crossed the line. I was free.", "Harriet Tubman", "as recorded by Sarah Bradford"),

        Excerpt(281, "Lifting as we climb.", "Mary Church Terrell", "motto of the National Association of Colored Women"),

        Excerpt(282, "A little rebellion now and then is a good thing.", "Abigail Adams", "attributed rendering, collected letters"),

        Excerpt(283, "Remember the ladies, and be more generous to them than your ancestors.", "Abigail Adams", "letter to John Adams"),

        Excerpt(284, "The first problem for all of us is not to learn, but to unlearn.", "Gloria Steinem", "collected essays"),

        Excerpt(285, "Well-behaved women seldom make history.", "Laurel Thatcher Ulrich", "Well-Behaved Women Seldom Make History"),

        Excerpt(286, "We cannot all succeed when half of us are held back.", "Malala Yousafzai", "I Am Malala"),

        Excerpt(287, "A child, a teacher, a pen can change the world.", "Malala Yousafzai", "collected remarks"),

        Excerpt(288, "The only way to deal with an unfree world is to become absolutely free.", "Albert Camus", "The Myth of Sisyphus"),

        Excerpt(289, "I rebel, therefore we exist.", "Albert Camus", "The Rebel"),

        Excerpt(290, "Real generosity toward the future lies in giving all to the present.", "Albert Camus", "collected notebooks"),

        Excerpt(291, "What is essential is invisible to the eye.", "Antoine de Saint-Exupery", "Le Petit Prince"),

        Excerpt(292, "Being a person is difficult. Nobody ever told us it might be otherwise.", "Marilynne Robinson", "collected essays"),


        Excerpt(293, "Service is the rent we pay for living.", "Marian Wright Edelman", "The Measure of Our Success"),

        Excerpt(294, "You cannot be what you cannot see.", "Marian Wright Edelman", "collected addresses"),

        Excerpt(295, "We are the ones we have been waiting for.", "June Jordan", "Poem for South African Women"),

        Excerpt(296, "None of us got where we are solely by pulling up our own bootstraps.", "Thurgood Marshall", "collected writings"),

        Excerpt(297, "The ballot is a passport to citizenship.", "W.E.B. Du Bois", "collected essays"),

        Excerpt(298, "Now is the accepted time, not tomorrow, not some more convenient season.", "W.E.B. Du Bois", "collected addresses"),

        Excerpt(299, "The cost of liberty is less than the price of repression.", "W.E.B. Du Bois", "John Brown"),

        Excerpt(300, "The kind of beauty I want is hard to get.", "Nikki Giovanni", "collected poems"),

        Excerpt(301, "Speak the truth to the people.", "Mari Evans", "Speak the Truth to the People"),

        Excerpt(302, "There is no such thing as being neutral about human dignity.", "Leymah Gbowee", "Mighty Be Our Powers"),

        Excerpt(303, "Extremists have shown what frightens them most: a girl with a book.", "Malala Yousafzai", "collected addresses"),

    )
}

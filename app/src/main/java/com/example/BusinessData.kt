package com.example

data class BusinessIdea(
    val id: String,
    val title: String,
    val sector: String,
    val budgetRange: BudgetRange,
    val geographicArea: GeographicArea,
    val skillType: SkillType,
    val description: String,
    val averageStartupCostAr: Long, // in Ariary
    val estimatedMonthlyProfitAr: Long,
    val requirements: List<String>,
    val steps: List<String>,
    val localRisks: List<String>,
    val opportunities: String,
    val expertTips: String
)

enum class BudgetRange(val label: String, val maxAr: Long) {
    LOW("Tetibola Kelely (< 500 000 Ar)", 500000L),
    MEDIUM("Tetibola Antonony (500 000 Ar - 2 000 000 Ar)", 2000000L),
    HIGH("Fampiasam-bola Be (> 2 000 000 Ar)", Long.MAX_VALUE)
}

enum class GeographicArea(val label: String) {
    URBAN("An-tanàn-dehibe (ohatra: Antananarivo, Toamasina, Mahajanga)"),
    RURAL("Ambanivohitra / Faritany (fambolena, fiompiana na morontsiraka)"),
    BOTH("Na aiza na aiza eto Madagasikara")
}

enum class SkillType(val label: String) {
    DIGITAL("Teknolojia, Serasera & Siansa"),
    MANUAL("Tanana, Asatanana, na Varotra"),
    AGRICULTURAL("Fambolena, Fiompiana, na tontolo ambanivohitra"),
    ANY("Tsy mila fahaizana manokana aloha")
}

data class TrainingModule(
    val number: Int,
    val title: String,
    val durationMin: Int,
    val summary: String,
    val contentSections: List<ContentSection>
)

data class ContentSection(
    val subtitle: String,
    val bulletPoints: List<String>,
    val malagasyContext: String // Highlight particularities like Mobile Money, informal sector, etc.
)

object BusinessRepository {
    val ideas = listOf(
        BusinessIdea(
            id = "poulet_gasy",
            title = "Fiompiana Akoho gasy hatsaraina (Akoho gasy)",
            sector = "Fiompiana & Fambolena",
            budgetRange = BudgetRange.MEDIUM,
            geographicArea = GeographicArea.RURAL,
            skillType = SkillType.AGRICULTURAL,
            description = "Ny fiompiana akoho gasy hatsaraina dia ahitana fitakiana mafy be noho ny fitiavan'ny Malagasy ny hena natoraly sy tsara tsiro. Mitaky fampiasam-bola kely kokoa izany raha oharina amin'ny akoho fakana nofo hafa hafarana avy any ivelany.",
            averageStartupCostAr = 800000L,
            estimatedMonthlyProfitAr = 400000L,
            requirements = listOf(
                "Tany kely mifefy tsara sy madio mirefy 20 m2 farafahakeliny",
                "Fividianana zana-borona (zana-borona gasy efa nahazo fanatsarana, iray volana)",
                "Sakafo voalanjalanja eo an-toerana (katsaka voatoto, tsinofon-bary, ary fako tourteau de soja)",
                "Vaksiny fototra (pestes, variole aviaire)"
            ),
            steps = listOf(
                "Manangana tranon'akoho madio, miditra rivotra tsara ary tsy misy hamandoana tamin'ny alalan'ny akora eo an-toerana (hazo, bararata, bozaka)",
                "Mividy akoho vavy 15 sy akoholahy gasy matanjaka 3 ho fanombohana",
                "Manaraka an-tsakany sy an-davany ny tetiandro famindrana vaksiny miaraka amin'ny mpitsabo biby eo amin'ny faritra misy anao",
                "Mikarakara ny fivarotana mivantana amin'ny mpanjifa na amin'ny alalan'ny mpamongady any an-tsena"
            ),
            localRisks = listOf(
                "Areti-mifindra mahery vaika (peste aviaire na barika) raha tara loatra ny fanaovana vaksiny",
                "Halatra akoho (matetika any ambanivohitra na faritany)",
                "Fahasarotan'ny fitaizana kely mandritra ny fotoana fahavaratra sy ny hatsiaka"
            ),
            opportunities = "Ny hena natoraly (gasy nentim-paharazana na bio) dia lafo 1.5 ka hatramin'ny 2 heny noho ny akoho de chair tsotra any amin'ny tanàn-dehibe toa an'Antananarivo sy Toamasina.",
            expertTips = "Varoty ny atody efa misy tsimoka ho akotrin'ny mpiompy hafa; manome vola haingana kokoa izany raha oharina amin'ny hena fotsiny."
        ),
        BusinessIdea(
            id = "redac_web",
            title = "Freelancing: Fanoratana amin'ny Internet & SEO",
            sector = "Asa amin'ny Internet",
            budgetRange = BudgetRange.LOW,
            geographicArea = GeographicArea.URBAN,
            skillType = SkillType.DIGITAL,
            description = "Lasa ivon-toeran'ny asa an-tariby sy BPO i Madagasikara. Rehefa manoratra ianao, dia afaka miara-miasa amin'ny orinasa eo an-toerana na mivantana amin'ny mpanjifa any ivelany miteny frantsay.",
            averageStartupCostAr = 350000L,
            estimatedMonthlyProfitAr = 600000L,
            requirements = listOf(
                "Solon-tsaina finday (ordinateur portable, na dia efa niasa aza)",
                "Fifandraisana Internet tsy miato (clé 4G Telma/Orange na fibre optique)",
                "Fifehezana tsara ny fiteny frantsay amin'ny soratra sy ny fitsipi-pitenenana",
                "Kaonty Mobile Money (Mvola, Orange Money na Airtel Money) handraisana ny karama"
            ),
            steps = listOf(
                "Mianatra ny fototry ny SEO (Search Engine Optimization) amin'ny alalan'ny fampianarana maimaim-poana amin'ny Internet",
                "Misoratra anarana amin'ny sehatra freelance (ComeUp, Upwork, Malt) na ao amin'ny vondrona Facebook misy mpanoratra",
                "Manoratra lahatsoratra modely 3 samihafa mba haseho amin'ny mpanjifa ho porofon'asa",
                "Mifandray amin'ireo orinasan-tserasera (BPO) any Tana (Ankorondrano, Isoraka, Andraharo) izay matetika mitady mpiara-miasa"
            ),
            localRisks = listOf(
                "Fahatapahan-jiro matetika (Délestage JIRAMA) izay manelingelina ny fandefasana asa ara-potoana",
                "Fiovan'ny fitsipiky ny milina fikarohana Google",
                "Fifaninanana mahery vaika amin'ny alalan'ny fampidinana vidiny ambany loatra"
            ),
            opportunities = "Ny fiasana ho an'ny mpanjifa frantsay na belza avy eto Madagasikara dia ahafahanao mahazo tombony be amin'ny vidiny ambony satria mihoatra ny salan-karama eto an-toerana izany.",
            expertTips = "Mividiana Powerbank matanjaka tsara ho an'ny router Wi-Fi anao, na miasa any amin'ny toerana fiasana iombonana (co-working) misy gropy rehefa misy délestage."
        ),
        BusinessIdea(
            id = "jus_fruits",
            title = "Fanaovana & Fivarotana Ranom-boankazo Natoraly",
            sector = "Sakafo & Fandrahoana",
            budgetRange = BudgetRange.LOW,
            geographicArea = GeographicArea.URBAN,
            skillType = SkillType.MANUAL,
            description = "Manankarena boankazo natoraly mahafinaritra i Madagasikara (manga any Mahajanga, litchis any Toamasina, corossol ary goyaves). Ny fanovana izany ho ranom-boankazo mangatsiaka tsy misy akora simika dia mitondra tombony be amin'ny maraina sy antoandro.",
            averageStartupCostAr = 250000L,
            estimatedMonthlyProfitAr = 350000L,
            requirements = listOf(
                "Mixeur tsara kalitao mirefy 500W farafahakeliny",
                "Tavoahangy plastika PET vaovao misy sarony azo hidiana mafy (azo vidiana ambongadiny ao Anosibe)",
                "Boankazo tsara kalitao vidiana maraina be any amin'ny tsenan'ny tantsaha",
                "Glacière kely hitazonana ny tavoahangy ho mangatsiaka tsara mandritra ny fivarotana"
            ),
            steps = listOf(
                "Mividy boankazo amin'ny vanim-potoana mety any amin'ny tsena ambongadiny (Anosibe na Isotry) mba hampihenana ny fandaniana",
                "Mamorona tsiro miavaka 3 (ohatra: Manga-Passion, Ananas-Gingembre, Corossol madio)",
                "Manasa sy manadio ny tavoahangy ary mametaka etikety misy ny anaranao sy ny laharan-telefaonanao",
                "Mizara izany maraina be ho an'ny mpiasan'ny birao, banky, ary mianatra any an-tsekoly"
            ),
            localRisks = listOf(
                "Fahasimbana haingana raha tsy mangatsiaka tsara ny toerana ametrahana azy",
                "Fiakaran'ny vidin'ny boankazo rehefa tsy vanim-potoanany",
                "Tsy fahampian'ny rano madio fisotro handasana ny fitaovana fandrahoana"
            ),
            opportunities = "Mihamaro ny olona mitady sakafo sy zava-pisotro natoraly ho an'ny fahasalamana eto Madagasikara. Haladin'ny olona ny ranom-boankazo simika misy siramamy be loatra.",
            expertTips = "Ampiasao siramamy mena natoraly (tanora) fa tsy siramamy fotsy mba hanomezana tsiro nentim-paharazana sy hisintonana mpanjifa be dia be."
        ),
        BusinessIdea(
            id = "apiculture",
            title = "Fiompiana Tantely sy Famokarana Tantely Natoraly",
            sector = "Fambolena / Tontolo Iainana",
            budgetRange = BudgetRange.HIGH,
            geographicArea = GeographicArea.RURAL,
            skillType = SkillType.AGRICULTURAL,
            description = "Ny tantely avy eto Madagasikara, indrindra ny tantely kininim-paka na litchis, dia tena tadiavin'ny olona eto an-toerana sy any ivelany. Ny tranon-tantely voakarakara tsara dia mamokatra volamena mitsoka nefa tsy mila fandaniana be isam-bolana.",
            averageStartupCostAr = 2500000L,
            estimatedMonthlyProfitAr = 1200000L,
            requirements = listOf(
                "Tany misy hazo be dia be na ambanivohitra lavitry ny fampiasana fanafody simika mandringana bibikely",
                "Fividianana tranon-tantely maoderina 5 hatramin'ny 10 (karazany Langstroth mety amin'ny toetr'andro mailaka)",
                "Fitaovam-piarovana (akanjo taloha matevina, fitaovana fampitsohana setroka)",
                "Fianarana teknika momba ny fitondran-tenan'ny tantely eto an-toerana (Apis mellifera unicolor)"
            ),
            steps = listOf(
                "Mametraka ny tranon-tantely amin'ny fiandohan'ny lohataona (Aogositra/Septambra)",
                "Misintona tantely avy amin'ny ala na mividy fianakavian-tantely amin'ny fikambanan'ny mpiompy tantely",
                "Manao fizahana isaky ny roa herinandro mba hiarovana amin'ny biby mpamabo sy hanamarinana ny fahasalaman'ny tantely",
                "Mijinja rehefa matoy tsara, manasivana azy tsara ary mampiditra azy amin'ny tavoahangy fitaratra 1kg na 500g"
            ),
            localRisks = listOf(
                "Doro-tanety sy doro-ala (Tavy) mamotika tranon-tantely sy vata",
                "Areti-tantely na fahasimban'ny tontolo iainana",
                "Fampifangaroana tantely sandoka eny an-tsena izay mampidina ny vidiny"
            ),
            opportunities = "Afaka miandry ela be ny tantely tsy simba. Mora ny mivarotra azy amin'ny fivarotam-panafody, fivarotana lehibe any Tana ary hôtely lehibe.",
            expertTips = "Manolora vokatra roa miavaka: ny iray ho an'ny fihinanana tsotra andavanandro, ary ny faharoa natoraly madio bio ho an'ny fitsaboana nentim-paharazana sy herboristerie."
        ),
        BusinessIdea(
            id = "friperie",
            title = "Fivarotana Akanjo Tonta (Friperie) amin'ny Facebook",
            sector = "Varotra & Lamaody",
            budgetRange = BudgetRange.MEDIUM,
            geographicArea = GeographicArea.URBAN,
            skillType = SkillType.MANUAL,
            description = "Ny 'bata friperie' avy any Eoropa na Azia no fototry ny fitafiana eto Madagasikara. Ny hevitra dia ny mividy bata tsara kalitao (Crème), manasa azy, mipasoka azy, maka sary tsara tarehy ary mivarotra izany amin'ny Facebook sy Instagram.",
            averageStartupCostAr = 600000L,
            estimatedMonthlyProfitAr = 500000L,
            requirements = listOf(
                "Tetibola hividianana tapa-bata voalohany amin'ny akanjo kalitao 'Crème' any Ambohipo na Isotry",
                "Fer mipasoka tsara sy savony manitra tsara kalitao hanasana ny akanjo",
                "Finday misy fakan-tsary tsara mba hanasongadinana ny hatsaran'ny akanjo",
                "Famoronana pejy Facebook mavitrika (Facebook mantsy no tambajotra sosialy voalohany eto amintsika)"
            ),
            steps = listOf(
                "Mividy bata mihidy avy amin'ny mpanafatra azo itokisana any amin'ny tsenan'Anosibe na Isotry",
                "Mifidy ny akanjo tsara indrindra, mpanasa azy tsara mba hanalana ny fofona friperie",
                "Maka sary anaovana na sary mipetaka tsara amin'ny mazava natoraly",
                "Mamoaka izany ho albin'akanjo any amin'ny Facebook, ary manao livraison eny Analakely na amin'ny alalan'ny mpanatitra entana"
            ),
            localRisks = listOf(
                "Tsy fahampian'ny kalitao anaty bata indraindray (miankina amin'ny vintana ny sasany)",
                "Mpanjifa mamandrika akanjo nefa tsy tonga amin'ny fotoana hifanomezana (ilay antsoina hoe 'faux-plan')",
                "Saran'ny internet tsy mitsahatra miakatra na fahatapahan'ny tambajotra"
            ),
            opportunities = "Ny tanora eto Madagasikara dia mitady fomba hitafiana tsara tarehy sy miavaka ary tsy lafo loatra. Aleon'izy ireo mividy efa nampasohina toy izay handany andro any Isotry.",
            expertTips = "Manolora livraison maimaim-poana eo Analakely rehefa mividy akanjo mihoatra ny roa ny mpanjifa: fomba tsara be hitehirizana mpanjifa mahatoky izany."
        ),
        BusinessIdea(
            id = "services_coursier",
            title = "Tolotra Fanatitran-drakitra & Entana amin'ny Môtô",
            sector = "Fitaterana & Tolotra",
            budgetRange = BudgetRange.HIGH,
            geographicArea = GeographicArea.URBAN,
            skillType = SkillType.MANUAL,
            description = "Ny fitohanana lava be eto Antananarivo dia manelingelina ny toekarena. Tena ilaina ny tolotra mpanatitra entana haingana amin'ny môtô na bisikileta ho an'ny taratasy ara-panjakana, entana maika na sakafo.",
            averageStartupCostAr = 3000000L,
            estimatedMonthlyProfitAr = 900000L,
            requirements = listOf(
                "Môtô matanjaka sy tsy mandany lasantsy be (ohatra: 110cc na 125cc efa niasa nefa tsara)",
                "Permis môtô manan-kery, taratasin-dàlana ary fiantohana (assurance) ara-dalàna",
                "Kitapo môtô lehibe tantera-drano sy mitazona hafanana (sac isotherme)",
                "Fampiharana sarintany an-tariby (ohatra: Maps.me na Google Maps)"
            ),
            steps = listOf(
                "Mividy môtô tsara kalitao ary manao ny fanamboarana sy vidange rehetra ilaina",
                "Mifandray amin'ireo mpivarotra amin'ny internet ao amin'ny Facebook mba ho lasa mpanatitra entana raikitra ho azy ireo",
                "Mametraka vidiny mazava tsara isaky ny faritra (ohatra: Anandrano/Analakely 4000 Ar, ivelan-tanàna 7000 Ar)",
                "Manome tolotra tsara sy haingana ary mandefa SMS fanamarinana foana rehefa voaray ny entana"
            ),
            localRisks = listOf(
                "Lozam-pifamoivoizana eny an-dalana sy fahasimban'ny môtô noho ny haratsian'ny làlana eto amintsika",
                "Fiakaran'ny vidin'ny solika tsy tapaka",
                "Sinto-mahery na sinto-baolina any amin'ny faritra maizina rehefa hariva ny andro"
            ),
            opportunities = "Ny firongatry ny fivarotana amin'ny Internet eto Tana dia mamorona livraison an'arivony isan'andro. Ny maha mpiara-miasa ofisialy azy ireo dia mitondra fidiram-bola tsy tapaka.",
            expertTips = "Manolora rafitra 'paiement à la livraison' (Raisinao ny volan'ilay entana any amin'ny mpanjifa vao averinao amin'ny mpivarotra amin'ny alalan'ny Mvola): tena tian'ny mpivarotra izany sady fitokisana lehibe."
        ),
        BusinessIdea(
            id = "raphia_art",
            title = "Fitaovana tamin'ny Raphia avo lenta",
            sector = "Asatanana",
            budgetRange = BudgetRange.MEDIUM,
            geographicArea = GeographicArea.URBAN,
            skillType = SkillType.MANUAL,
            description = "Ny raphia avy eto Madagasikara dia anisan'ny tsara indrindra eran-tany. Ny famadihana izany ho satroka tsara tarehy, kitapo fandehanana any an-driaka, na haingon-trano maoderina dia fandraharahana tena ahazoana tombony amin'ny vazaha sy ny fizahantany.",
            averageStartupCostAr = 450000L,
            estimatedMonthlyProfitAr = 550000L,
            requirements = listOf(
                "Raphia manta vidiana mivantana amin'ny mpamokatra (ohatra: faritry ny Majunga na Toamasina)",
                "Fandokoana natoraly avy amin'ny ravinkazo na akora tsotra, ary fanjaitra maro samihafa",
                "Saina tia famoronana sy faharetana mba hahazoana asa madio sy tsara rary",
                "Fampiasana ny tambajotra sosialy iraisam-pirenena (Instagram, Etsy, Pinterest) hanehoana ny zava-bita"
            ),
            steps = listOf(
                "Mianatra rary raphia amin'ny alalan'ny crochet na fomba rary isan-karazany",
                "Manamboatra kitapo 5 voalohany ho santionany, ampifandraisina amin'ny Lamba gasy eo anatiny",
                "Mandray anjara amin'ny tsenaben'ny asatanana any Tana (ohatra: Foire Internationale de Madagascar na tsenan'ny tantsaha)",
                "Miara-miasa amin'ireo fivarotana fahatsiarovana any amin'ny faritra fizahantany toy ny Nosy Be, Sainte Marie, na Morondava"
            ),
            localRisks = listOf(
                "Saran'ny fandefasana entana any ivelany izay tena lafo dia lafo (DHL/FedEx)",
                "Asa miankina amin'ny fotoam-pialan-tsasatra sy ny fahatongavan'ny mpizahatany",
                "Kopia na fakana tahaka ny sary sy ny hevitra avy amin'ny mpifaninana hafa"
            ),
            opportunities = "Ny asatanana 'Made in Madagascar' dia manana laza tsara dia tsara eran-tany noho ny maha natoraly sy faharetany sy ny fahaizan'ny tanana malgache.",
            expertTips = "Asio soratra kely milaza ny tantaran'ilay vehivavy nandrafitra ilay kitapo teo an-toerana: tena tia mahafantatra izany ny mpizahatany ary vonona handoa vola bebe kokoa hanohanana ny fampandrosoana ara-tsosialy."
        )
    )

    val modules = listOf(
        TrainingModule(
            number = 1,
            title = "Fanamarinana ny Hevitra & Fikarohana eny an-kianja",
            durationMin = 15,
            summary = "Ahoana no ahazoana antoka fa hahaliana ny mpanjifa tokoa ny hevitrao alohan'ny handanianao ny volanao.",
            contentSections = listOf(
                ContentSection(
                    subtitle = "1. Ny fijerena mivantana ny filaharana sy ny tsena",
                    bulletPoints = listOf(
                        "Jereo tsara ireo mpifaninana aminao eo an-tanàna na amin'ny quartier (ohatra: mangahazo, mpivarotra kojakoja).",
                        "Isao ny isan'ny mpanjifa mandalo sy mividy ao aminy mandritra ny fotoana 3 samy hafa amin'ny antoandro.",
                        "Fantaro izay zavatra tadiavin'ny mpanjifa nefa tsy omen'ilay mpifaninana tsara (ohatra: fandraisana sariaka, fahadiovana, mpanatitra entana)."
                    ),
                    malagasyContext = "Eto Madagasikara, amin'ny alalan'ny fifandraisana tsara no iorenan'ny tamberim-bidy sy ny fahatokisana. Ny firesahana am-pilaminana sy am-pisakafoana amin'ny olona eny an-tsena toa an'Anosibe no fanadihadiana tsena tsara indrindra sady maimaim-poana."
                ),
                ContentSection(
                    subtitle = "2. Tetika fanombanana amin'ny olona vitsy (MVP)",
                    bulletPoints = listOf(
                        "Aza mbola mividy entana betsaka rehefa manomboka. Manamboara santionany 5 hatramin'ny 10 ihany aloha.",
                        "Andramo amidy amin'ny namana, mpiara-belona, na ao amin'ny vondrona Facebook misy anao izany vokatra izany.",
                        "Raha faly sy mividy haingana izany ny olona, dia midika izany fa azo antoka ny hevitrao ho lasa fandraharahana !"
                    ),
                    malagasyContext = "Aza mandany ny renivolanao rehetra hividianana fitaovana lafo vidy na hanofana trano avy hatrany. Manomboha amin'izay kely anananao ao an-trano."
                )
            )
        ),
        TrainingModule(
            number = 2,
            title = "Fitadiavana Renivola & Fitantanana ny Vola miditra",
            durationMin = 20,
            summary = "Ahoana no hitantanana ny vola eo am-pelatanana, hisorohana ny fatiantoka noho ny trosa na findramam-bola tsy voaloa.",
            contentSections = listOf(
                ContentSection(
                    subtitle = "1. Ny fandrika lehibe indrindra: fivarotana amin'ny trosa ('Vidiny amin'ny trosa')",
                    bulletPoints = listOf(
                        "Eto Madagasikara, misy fironana ara-tsosialy matetika amin'ny fifanampiana izay mitarika fivarotana amin'ny trosa ho an'ny namana sy ny fianakaviana.",
                        "Fitsipika volamena: tsy mivarotra trosa mihitsy rehefa manomboka. Ny renivolanao dia natao hampandehanana ny fandraharahana fa tsy ho drafi-piahiana ara-tsosialy miankina aminao.",
                        "Apetraho tsara ny soratra mazava na ambarao am-panajana sy am-pahatsorana hoe: 'Mba ho fisorohana ny fatiantoka dia tsy mivarotra mindram-bola izahay'."
                    ),
                    malagasyContext = "Ny tsy fahampian'ny vola mivezivezy (trésorerie) no antony voalohany mahatonga ny 80% amin'ny fandraharahana kely hikatona ao anatin'ny herintaona voalohany eto Antananarivo."
                ),
                ContentSection(
                    subtitle = "2. Toerana azo hitadiavana fanampiana ara-bola",
                    bulletPoints = listOf(
                        "Ny tahiry manokana: vola kely voahangona avy amin'ny asa madinika nifanesy teo aloha.",
                        "Ireo rantsana madinika fampindramam-bola (microfinance): fikambanana toa ny CEM, SIPEM, Access Banque, sa Baobab dia manolotra findramam-bola kely mety amin'ny asa madinika.",
                        "Ireo fifaninanana tetikasa ho an'ny tanora (ohatra: NextA, Orange Digital Center, na ny fanohanana samihafa omen'ny ministera)."
                    ),
                    malagasyContext = "Lafo dia lafo ny zana-bola any amin'ny banky lehibe mahazatra (matetika > 18%). Aleo manomboka tsikelikely amin'ny tahiry manokana alohan'ny hindramana vola be misy zana-bola mavesatra."
                )
            )
        ),
        TrainingModule(
            number = 3,
            title = "Ny Lalàna & ny Fisoratana Anarana ofisialy",
            durationMin = 15,
            summary = "Ny fomba fialana amin'ny sehatry ny tsy ara-dalàna mba hahazoana tsena lehibe miaraka amin'ny fanjakana na orinasa lehibe.",
            contentSections = listOf(
                ContentSection(
                    subtitle = "1. Ireo karazana orinasa mety amin'ny fanombohana",
                    bulletPoints = listOf(
                        "Orinasa Olon-tokana (Entreprise Individuelle - EI): Tena tsotra be ny fisoratana anarana ary tsy mila renivola farany ambany.",
                        "Ny SARL (Société à Responsabilité Limitée): Raha misy mpiara-miombon'antoka maromaro, mba tsy hifangaroan'ny volanao manokana sy ny volan'ny orinasa."
                    ),
                    malagasyContext = "Ny birao tokana EDBM (Economic Development Board of Madagascar) eny Antaninarenina dia ahafahana manoratra orinasa ao anatin'ny 3 andro monja amin'ny sarany ambany dia ambany (latsaky ny 150 000 Ar ho an'ny Entreprise Individuelle)."
                ),
                ContentSection(
                    subtitle = "2. Ny fahafantarana ny momba ny Impôt Synthétique (IS)",
                    bulletPoints = listOf(
                        "Ny Impôt Synthétique dia natao manokana ho an'ireo mpandraharaha madinika izay manana vola miditra mivezivezy latsaky ny fetra farany voalaza.",
                        "Matetika izany dia eo amin'ny 5% amin'ny fitambaran'ny vola miditra isan-taona nahavitana varotra.",
                        "Ny fananana karatra maha-ara-dalàna anao dia ahafahanao manome faktiora ofisialy ho an'ny hôtely, fivarotana lehibe, na orinasa lehibe izay mila izany."
                    ),
                    malagasyContext = "Raha mijanona amin'ny sehatry ny tsy ara-dalàna mandrakizay ianao, dia ho voafetra foana ny fivoaran'ny fandraharahanao satria tsy afaka hividy aminao ny mpanjifa lehibe."
                )
            )
        ),
        TrainingModule(
            number = 4,
            title = "Ny Fivarotana & ny Serasera ho an'ny mpanjifa",
            durationMin = 18,
            summary = "Ny fampiasana ny Facebook amin'ny fomba mahomby, ny Mobile Money, ary ny herin'ny 'resaka mifampita' (bouche-à-oreille).",
            contentSections = listOf(
                ContentSection(
                    subtitle = "1. Facebook, ny Internet tsy azo ialana eto Madagasikara",
                    bulletPoints = listOf(
                        "Eto Madagasikara, ny ankamaroan'ny olona dia mampiasa Facebook noho ireo tolotra sy forfait misy 'Facebook gratuit na illimité'.",
                        "Manorata pejy matihanina tsara, asio sary madio, ary valio haingana dia haingana ny fanontanian'ny mpanjifa.",
                        "Soraty mivantana amin'ny sary ny vidiny fa aza miteny foana hoe 'Valiana an-kafatra manokana (MP)'. Ny fahafantaran'ny mpanjifa ny vidiny mivantana dia mampitombo ny fahatokisana sady manafaingana ny varotra."
                    ),
                    malagasyContext = "Ny ankamaroan'ny mponina an-tanàn-dehibe dia mitady entana hovidiana mivantana amin'ny alalan'ny fikarohana teny-fanalahidy amin'ny Facebook."
                ),
                ContentSection(
                    subtitle = "2. Ny fampiasana ny fandoavam-bola Mobile Money",
                    bulletPoints = listOf(
                        "Omeo fahafahana handoa vola amin'ny alalan'ny Mvola, Orange Money na Airtel Money ny mpanjifa.",
                        "Ny fampiasana izany dia misoroka ny fitondrana vola fitaratra any am-paosy izay mampitombo ny filaminana any an-dalambe.",
                        "Manokrasa kaonty 'Marchand' na 'Pay' mba hampihenana ny saram-pisintonana halain'ny mpanjifa rehefa handoa vola aminao izy ireo."
                    ),
                    malagasyContext = "Noho ny fahavitsian'ny olona manana kaonty any amin'ny banky (< 15%), ny Mobile Money no nanjary fitaovana fandoavam-bola voalohany amin'ny varotra an-tariby eto Madagasikara."
                )
            )
        ),
        TrainingModule(
            number = 5,
            title = "Mpanampy Virtoaly & Fandraharahana an-tsoratra (Virtual Assistant)",
            durationMin = 25,
            summary = "Ianaro ny fototry ny fikarakarana mailaka, tetiandro, ary tolotra ho an'ny mpanjifa any ivelany amin'ny fomba maharitra.",
            contentSections = listOf(
                ContentSection(
                    subtitle = "1. Ny asa andavanandron'ny Virtual Assistant (VA)",
                    bulletPoints = listOf(
                        "Fandaminana the tetiandro (Calendar management) sy fivoriana iraisam-pirenena mifanaraka amin'ny fuseau horaire samihafa.",
                        "Famaliana mailaka amin'ny fomba matihanina sy fikarakarana antontan-taratasy (Word, Excel, Google Suite, Notion).",
                        "Fampiasana fitaovana fanampiana mpanjifa (Customer Support / CRM) toy ny Zendesk, Freshdesk, na amin'ny alalan'ny mailaka sy chat mivantana."
                    ),
                    malagasyContext = "Mitaky fifehezana tsara ny fiteny frantsay (na anglisy) an-tsoratra sy am-bava ny asa Virtual Assistant. Afaka manomboka any an-trano ianao raha manana solosaina sy internet tsara."
                ),
                ContentSection(
                    subtitle = "2. Ireo fitaovana fototra tsy maintsy hofehezina",
                    bulletPoints = listOf(
                        "Fampiharana fiaraha-miasa sy fandrindrana asa: Slack, Trello, Asana, Teams, ary Zoom na Google Meet ho an'ny fivoriana.",
                        "Fitaovana fanitsiana diso sy fanatsarana ny fanoratana toy ny LanguageTool, Grammarly, na MerciApp.",
                        "Tombony lehibe: Fahafantarana ny fomba fampiasana fitaovana AI (toa ny ChatGPT na Gemini) hanafainganana ny fanoratana tatitra na imailaka."
                    ),
                    malagasyContext = "Maro ny fampianarana maimaim-poana amin'ny YouTube na OpenClassrooms momba ireto fitaovana ireto. Aza misalasala manao an-tsary sy manaraka fampiharana mivantana."
                )
            )
        ),
        TrainingModule(
            number = 6,
            title = "Fidirana amin'ny tontolon'ny Programation & Kodiana (Software Development)",
            durationMin = 30,
            summary = "Torohevitra mivaingana hianarana mamorona tranonkala sy fampiharana finday maharitra mba hahazoana asa amin'ny maha Codeur azy.",
            contentSections = listOf(
                ContentSection(
                    subtitle = "1. Ny lalan-tsara amin'ny fianarana teny fandaharana (Programming)",
                    bulletPoints = listOf(
                        "Atombohy amin'ny fototry ny tranonkala: HTML5, CSS3 mba hahay handrafitra sy handravaka pejy tsotra.",
                        "Ianaro ny JavaScript (na Python) mba hitondrana fahasamihafana sy fahaiza-mihetsika (dynamisme) amin'ny tranonkala.",
                        "Mandrosoa amin'ny Frameworks be mpitady toa ny React, Vue, na fikorakojana amin'ny mpiara-miasa amin'ny alalan'ny Git sy GitHub."
                    ),
                    malagasyContext = "Ny asa fampivoarana rindrambaiko (Développeur Web/Mobile) no anisan'ny asa be karama indrindra eto Madagasikara (eo amin'ny 1 200 000 Ar ka hatramin'ny 4 500 000 Ar+ isam-bolana araka ny fahaiza-manao)."
                ),
                ContentSection(
                    subtitle = "2. Fananganana Portfolio sy fitadiavana mpanjifa (Clients)",
                    bulletPoints = listOf(
                        "Manamboara tetikasa kely andramana 3 farafahakeliny (ohatra: tranonkala fivarotana madinika, diary an-tsoratra, sns) haseho ho porofon'ny fahaiza-manao.",
                        "Sokafy the kaonty LinkedIn sy Upwork, fenoy tsara ny mombamomba anao ary ampidiro ao ny tetikasa vitanao.",
                        "Manorata tamberim-bidy tsara amin'ny mpanjifa voalohany amin'ny fampidinana kely ny sarany mba hananganana anarana tsara (Reviews)."
                    ),
                    malagasyContext = "Ny fidirana amin'ny vondrom-piarahamonina eo an-toerana (any amin'ny Facebook na Discord, DevFest, HackerHouse) dia sady ahafahana mianatra no ahazoana tolotra asa raikitra voalohany avy amin'ireo zokiny."
                )
            )
        ),
        TrainingModule(
            number = 7,
            title = "Digital Marketing & Serasera amin'ny Tambajotra (Social Media)",
            durationMin = 20,
            summary = "Ianaro ny fomba fampiroboroboana orinasa amin'ny Facebook sy Instagram mba hahazoana asa maharitra amin'ny maha Community Manager azy.",
            contentSections = listOf(
                ContentSection(
                    subtitle = "1. Ny asa fototry ny Community Manager (CM)",
                    bulletPoints = listOf(
                        "Famoronana votoaty mahavariana (sary, soratra, horonantsary fohy) amin'ny fampiasana fitaovana toy ny Canva na CapCut.",
                        "Copywriting: Ny fahaizana manoratra lahatsoratra mahasarika ny mpanjifa mba hividy na hanaraka ny pejy.",
                        "Fandrindrana pejy (Modération): Ny famaliana haingana sy am-panajana ny fanamarihana sy ny hafatra miafina (MP) ary ny famonoana ny fanehoan-kevitra ratsy na spam."
                    ),
                    malagasyContext = "Tena mandeha be ny CM eto Madagasikara satria vitsy ny orinasa manana info hikarakarana ny pejy Facebook-ny andavanandro. Mety hahazoana karama isam-bolana tsara avy amin'ny orinasa madinika eo an-toerana."
                ),
                ContentSection(
                    subtitle = "2. Ny dokam-barotra mandray vola (Facebook Ads & Google Ads)",
                    bulletPoints = listOf(
                        "Fianarana ny rafitra Meta Business Suite mba ahafahana manao dokam-barotra voakendry tsara (Targeting) mifanaraka amin'ny taona sy faritra.",
                        "Fitantanana ny tetibola mba tsy ho fatiantoka ary fandinihana ny tondro fahombiazana (Cost per Click, Reach, Impressions).",
                        "Famaly ny vokatra amin'ny alalan'ny tatitra tsotra sy mazava ho an'ny tompon'ny orinasa mba hahitana ny fiverenan'ny renivola (ROI)."
                    ),
                    malagasyContext = "Raha mahay mampitombo ny varoty ny mpandraharaha iray amin'ny alalan'ny dokam-barotra ianao, dia ho mpiara-miasa maharitra sy manan-danja lehibe ho azy ianao."
                )
            )
        )
    )
}

package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.MyApplicationTheme
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MainNavigationContainer(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Custom Colors matching Madagascar Flag Accent but softened for professional UI
val MadaGreen = Color(0xFF2E7D32) // Vert Forêt
val MadaRed = Color(0xFFC62828)   // Rouge Brique
val MadaYellow = Color(0xFFF9A825) // Jaune Or pour avertissements/risques
val SoftGreen = Color(0xFFE8F5E9)  // Fond vert très clair
val SoftBg = Color(0xFFF4F6F4)     // Gris vert très clair
val Charcoal = Color(0xFF212121)   // Texte principal
val CardBg = Color(0xFFFFFFFF)     // Fond de carte blanc pur

fun formatAr(amount: Long): String {
    val formatter = DecimalFormat("#,###")
    return "${formatter.format(amount)} Ar"
}

@Composable
fun MainNavigationContainer(modifier: Modifier = Modifier) {
    var selectedTab by remember { mutableStateOf(0) }
    var likedIdeaIds by remember { mutableStateOf(setOf<String>()) }
    var selectedIdeaForDetail by remember { mutableStateOf<BusinessIdea?>(null) }
    
    // For Simulator presets loaded from Catalog
    var simulateCapital by remember { mutableStateOf("800000") }
    var simulatePrice by remember { mutableStateOf("15000") }
    var simulateCost by remember { mutableStateOf("8000") }
    var simulateQty by remember { mutableStateOf("60") }
    var simulateFixed by remember { mutableStateOf("60000") }
    var simulatorTitle by remember { mutableStateOf("Fandaniako manokana") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SoftBg)
    ) {
        // App Custom Header
        TopAppBarHeader()

        // Tab Row fully in Malagasy
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = CardBg,
            contentColor = MadaGreen,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = MadaGreen
                )
            }
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("Hevitra", fontWeight = FontWeight.Bold, fontSize = 12.sp) },
                icon = { Icon(Icons.Default.Menu, contentDescription = null, modifier = Modifier.size(20.dp)) },
                selectedContentColor = MadaGreen,
                unselectedContentColor = Color.Gray
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("Sivana", fontWeight = FontWeight.Bold, fontSize = 12.sp) },
                icon = { Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(20.dp)) },
                selectedContentColor = MadaGreen,
                unselectedContentColor = Color.Gray
            )
            Tab(
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 },
                text = { Text("Fianarana", fontWeight = FontWeight.Bold, fontSize = 12.sp) },
                icon = { Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(20.dp)) },
                selectedContentColor = MadaGreen,
                unselectedContentColor = Color.Gray
            )
            Tab(
                selected = selectedTab == 3,
                onClick = { selectedTab = 3 },
                text = { Text("Kajy Tombony", fontWeight = FontWeight.Bold, fontSize = 11.sp) },
                icon = { Icon(Icons.Default.Info, contentDescription = null, modifier = Modifier.size(20.dp)) },
                selectedContentColor = MadaGreen,
                unselectedContentColor = Color.Gray
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            when (selectedTab) {
                0 -> CatalogScreen(
                    likedIdeaIds = likedIdeaIds,
                    onToggleLike = { id ->
                        likedIdeaIds = if (likedIdeaIds.contains(id)) {
                            likedIdeaIds - id
                        } else {
                            likedIdeaIds + id
                        }
                    },
                    onSelectIdea = { selectedIdeaForDetail = it },
                    onLoadIntoCalculator = { idea ->
                        // Load into simulator states
                        simulatorTitle = idea.title
                        simulateCapital = idea.averageStartupCostAr.toString()
                        simulatePrice = when(idea.id) {
                            "poulet_gasy" -> "15000"
                            "redac_web" -> "12000"
                            "jus_fruits" -> "2500"
                            "apiculture" -> "18000"
                            "friperie" -> "8000"
                            "services_coursier" -> "5000"
                            "raphia_art" -> "25000"
                            else -> "10000"
                        }
                        simulateCost = when(idea.id) {
                            "poulet_gasy" -> "8000"
                            "redac_web" -> "1000"
                            "jus_fruits" -> "1000"
                            "apiculture" -> "2000"
                            "friperie" -> "3000"
                            "services_coursier" -> "1500"
                            "raphia_art" -> "8000"
                            else -> "4000"
                        }
                        simulateQty = when(idea.id) {
                            "poulet_gasy" -> "60"
                            "redac_web" -> "120"
                            "jus_fruits" -> "400"
                            "apiculture" -> "80"
                            "friperie" -> "150"
                            "services_coursier" -> "220"
                            "raphia_art" -> "40"
                            else -> "100"
                        }
                        simulateFixed = when(idea.id) {
                            "poulet_gasy" -> "60000"
                            "redac_web" -> "80000"
                            "jus_fruits" -> "50000"
                            "apiculture" -> "30000"
                            "friperie" -> "60000"
                            "services_coursier" -> "120000"
                            "raphia_art" -> "40000"
                            else -> "50000"
                        }
                        selectedTab = 3 // Jump directly to simulator
                    }
                )
                1 -> QuizScreen(
                    onSelectIdea = { selectedIdeaForDetail = it }
                )
                2 -> TrainingScreen()
                3 -> SimulationScreen(
                    initialCapital = simulateCapital,
                    initialPrice = simulatePrice,
                    initialCost = simulateCost,
                    initialQty = simulateQty,
                    initialFixed = simulateFixed,
                    projectTitle = simulatorTitle,
                    onValuesChanged = { cap, pr, co, qty, fix, tit ->
                        simulateCapital = cap
                        simulatePrice = pr
                        simulateCost = co
                        simulateQty = qty
                        simulateFixed = fix
                        simulatorTitle = tit
                    }
                )
            }

            // Interactive Bottom Sheet / Detail Overlay for selected idea details
            selectedIdeaForDetail?.let { idea ->
                IdeaDetailDialog(
                    idea = idea,
                    isLiked = likedIdeaIds.contains(idea.id),
                    onToggleLike = {
                        likedIdeaIds = if (likedIdeaIds.contains(idea.id)) {
                            likedIdeaIds - idea.id
                        } else {
                            likedIdeaIds + idea.id
                        }
                    },
                    onClose = { selectedIdeaForDetail = null },
                    onLoadIntoCalculator = {
                        simulatorTitle = idea.title
                        simulateCapital = idea.averageStartupCostAr.toString()
                        simulatePrice = when(idea.id) {
                            "poulet_gasy" -> "15000"
                            "redac_web" -> "12000"
                            "jus_fruits" -> "2500"
                            "apiculture" -> "18000"
                            "friperie" -> "8000"
                            "services_coursier" -> "5000"
                            "raphia_art" -> "25000"
                            else -> "10000"
                        }
                        simulateCost = when(idea.id) {
                            "poulet_gasy" -> "8000"
                            "redac_web" -> "1000"
                            "jus_fruits" -> "1000"
                            "apiculture" -> "2000"
                            "friperie" -> "3000"
                            "services_coursier" -> "1500"
                            "raphia_art" -> "8000"
                            else -> "4000"
                        }
                        simulateQty = when(idea.id) {
                            "poulet_gasy" -> "60"
                            "redac_web" -> "120"
                            "jus_fruits" -> "400"
                            "apiculture" -> "80"
                            "friperie" -> "150"
                            "services_coursier" -> "220"
                            "raphia_art" -> "40"
                            else -> "100"
                        }
                        simulateFixed = when(idea.id) {
                            "poulet_gasy" -> "60000"
                            "redac_web" -> "80000"
                            "jus_fruits" -> "50000"
                            "apiculture" -> "30000"
                            "friperie" -> "60000"
                            "services_coursier" -> "120000"
                            "raphia_art" -> "40000"
                            else -> "50000"
                        }
                        selectedIdeaForDetail = null
                        selectedTab = 3 // Jump to simulator
                    }
                )
            }
        }
    }
}

@Composable
fun TopAppBarHeader() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(0.dp)),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Madagascar Flag Color Vertical Stripes mock
                Row(
                    modifier = Modifier
                        .height(32.dp)
                        .width(42.dp)
                        .clip(RoundedCornerShape(4.dp))
                ) {
                    Box(modifier = Modifier.weight(1.3f).fillMaxHeight().background(Color.White))
                    Column(modifier = Modifier.weight(2f).fillMaxHeight()) {
                        Box(modifier = Modifier.weight(1f).fillMaxWidth().background(MadaRed))
                        Box(modifier = Modifier.weight(1f).fillMaxWidth().background(MadaGreen))
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Business Mada",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MadaGreen
                    )
                    Text(
                        text = "Hevitra Fandraharahana & Fampianarana ho an'ny Mpandraharaha",
                        fontSize = 11.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
fun CatalogScreen(
    likedIdeaIds: Set<String>,
    onToggleLike: (String) -> Unit,
    onSelectIdea: (BusinessIdea) -> Unit,
    onLoadIntoCalculator: (BusinessIdea) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedSectorFilter by remember { mutableStateOf("Rehetra") }
    
    val allSectors = listOf("Rehetra") + BusinessRepository.ideas.map { it.sector }.distinct()

    val filteredIdeas = BusinessRepository.ideas.filter {
        (selectedSectorFilter == "Rehetra" || it.sector == selectedSectorFilter) &&
        (it.title.contains(searchQuery, ignoreCase = true) ||
         it.description.contains(searchQuery, ignoreCase = true) ||
         it.sector.contains(searchQuery, ignoreCase = true))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)
    ) {
        // Welcome Header Banner
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            colors = CardDefaults.cardColors(containerColor = SoftGreen),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = MadaGreen,
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Mitadiava Hevitra Mahomby",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = MadaGreen
                    )
                    Text(
                        text = "Ohabatasa sy fandraharahana ara-toekarena mifanaraka amin'ny fiainana eto Madagasikara amin'izao 2026 izao.",
                        fontSize = 11.sp,
                        color = Charcoal.copy(alpha = 0.82f)
                    )
                }
            }
        }

        // Search Text Field in Malagasy
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Mikaroha sehatra, teny fanalahidy...", fontSize = 13.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = MadaGreen) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MadaGreen,
                unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = CardBg,
                unfocusedContainerColor = CardBg
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Horizontal Category Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Sehatra : ", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray)
            Spacer(modifier = Modifier.width(4.dp))
            
            Card(
                colors = CardDefaults.cardColors(containerColor = CardBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    allSectors.take(4).forEach { sector ->
                        val isSelected = selectedSectorFilter == sector
                        Text(
                            text = if (sector.length > 12) sector.take(9) + "..." else sector,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) Color.White else Charcoal,
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isSelected) MadaGreen else Color.Transparent)
                                .clickable { selectedSectorFilter = sector }
                                .padding(horizontal = 8.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // List of filtered ideas
        if (filteredIdeas.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Tsy misy hevitra mifanaraka amin'ny karokao.",
                        fontSize = 13.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filteredIdeas) { idea ->
                    IdeaCard(
                        idea = idea,
                        isLiked = likedIdeaIds.contains(idea.id),
                        onToggleLike = { onToggleLike(idea.id) },
                        onClick = { onSelectIdea(idea) },
                        onLoadIntoCalculator = { onLoadIntoCalculator(idea) }
                    )
                }
            }
        }
    }
}

@Composable
fun IdeaCard(
    idea: BusinessIdea,
    isLiked: Boolean,
    onToggleLike: () -> Unit,
    onClick: () -> Unit,
    onLoadIntoCalculator: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = CardBg),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            // Sector tag & Like icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = idea.sector,
                    fontSize = 11.sp,
                    color = MadaGreen,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(MadaGreen.copy(alpha = 0.12f))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onToggleLike,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Bookmark",
                            tint = if (isLiked) MadaRed else Color.LightGray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Title
            Text(
                text = idea.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Charcoal
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Short Desc
            Text(
                text = idea.description,
                fontSize = 12.sp,
                color = Color.DarkGray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Info markers in Malagasy
            Divider(color = Color.LightGray.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Renivola fanombohana", fontSize = 10.sp, color = Color.Gray)
                    Text(
                        text = formatAr(idea.averageStartupCostAr),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MadaRed
                    )
                }

                Column {
                    Text("Tombony isam-bolana", fontSize = 10.sp, color = Color.Gray)
                    Text(
                        text = "~ " + formatAr(idea.estimatedMonthlyProfitAr),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MadaGreen
                    )
                }

                Button(
                    onClick = onClick,
                    modifier = Modifier.height(30.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MadaGreen)
                ) {
                    Text("Andsipiriany", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// Dialog to showcase all specific details of a chosen Business Idea in Malagasy
@Composable
fun IdeaDetailDialog(
    idea: BusinessIdea,
    isLiked: Boolean,
    onToggleLike: () -> Unit,
    onClose: () -> Unit,
    onLoadIntoCalculator: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.55f))
            .clickable { onClose() },
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .clickable(enabled = false) {}, // do not trigger close on card click
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            colors = CardDefaults.cardColors(containerColor = CardBg)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                // Header of Bottomsheet
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = idea.sector,
                        fontSize = 11.sp,
                        color = MadaGreen,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(MadaGreen.copy(alpha = 0.1f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )

                    Row {
                        IconButton(onClick = onToggleLike) {
                            Icon(
                                  imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                tint = if (isLiked) MadaRed else Color.Gray
                            )
                        }
                        IconButton(onClick = onClose) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Fermer"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = idea.title,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Charcoal
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Scrollable main sheet contents
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = idea.description,
                        fontSize = 13.sp,
                        color = Color.DarkGray,
                        lineHeight = 18.sp
                    )
                    
                    Spacer(modifier = Modifier.height(14.dp))

                    // Quick Financial cards in Malagasy
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Card(
                            modifier = Modifier.weight(1f),
                            colors = CardDefaults.cardColors(containerColor = SoftGreen),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text("Tombotombana Renivola", fontSize = 10.sp, color = Color.Gray)
                                Text(
                                    text = formatAr(idea.averageStartupCostAr),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MadaRed
                                )
                                Text("Fandaniana hanombohana", fontSize = 9.sp, color = Color.DarkGray)
                            }
                        }

                        Card(
                            modifier = Modifier.weight(1f),
                            colors = CardDefaults.cardColors(containerColor = SoftGreen),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text("Tombony Madio Heverina", fontSize = 10.sp, color = Color.Gray)
                                Text(
                                    text = "~ " + formatAr(idea.estimatedMonthlyProfitAr) + "/VOLANA",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MadaGreen
                                )
                                Text("Rehefa mandeha tsara", fontSize = 9.sp, color = Color.DarkGray)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Needs list in Malagasy
                    Text("📋 Fitaovana & Zavatra ilaina :", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Charcoal)
                    Spacer(modifier = Modifier.height(4.dp))
                    idea.requirements.forEach { req ->
                        Row(
                            modifier = Modifier.padding(vertical = 3.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = MadaGreen,
                                modifier = Modifier
                                    .size(16.dp)
                                    .padding(top = 2.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(req, fontSize = 12.sp, color = Color.DarkGray)
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Action Steps in Malagasy
                    Text("🚀 Dingana lehibe hanombohana azy :", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Charcoal)
                    Spacer(modifier = Modifier.height(4.dp))
                    idea.steps.forEachIndexed { index, step ->
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .background(MadaGreen, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = (index + 1).toString(),
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(step, fontSize = 12.sp, color = Color.DarkGray)
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Risks warning box in Malagasy
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MadaYellow.copy(alpha = 0.12f)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Warning, contentDescription = null, tint = MadaYellow, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("🚨 Loza sy Sakana mety hitranga :", fontWeight = FontWeight.Bold, color = Charcoal, fontSize = 12.sp)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            idea.localRisks.forEach { risk ->
                                Text("- $risk", fontSize = 11.sp, color = Color.DarkGray, modifier = Modifier.padding(vertical = 2.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Opportunities
                    Text("💡 Nahoana no tena ahazoana tombony :", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = MadaGreen)
                    Text(idea.opportunities, fontSize = 12.sp, color = Color.DarkGray, modifier = Modifier.padding(top = 2.dp))

                    Spacer(modifier = Modifier.height(12.dp))

                    // Expert Tips
                    Text("⭐ Soso-kevitra avy amin'ny manampahaizana :", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = MadaRed)
                    Text(idea.expertTips, fontSize = 12.sp, color = Color.DarkGray, modifier = Modifier.padding(top = 2.dp))

                    Spacer(modifier = Modifier.height(20.dp))
                }

                // Call To Action (to simulate this business model)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = onClose,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = MadaRed)
                    ) {
                        Text("Hiverina", fontSize = 12.sp)
                    }

                    Button(
                        onClick = onLoadIntoCalculator,
                        modifier = Modifier.weight(1.5f),
                        colors = ButtonDefaults.buttonColors(containerColor = MadaGreen)
                    ) {
                        Icon(Icons.Default.Info, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Kajio ny Tombony", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

// Tab 2: Interactive Questionnaires to filter / output ideas in Malagasy
@Composable
fun QuizScreen(onSelectIdea: (BusinessIdea) -> Unit) {
    var step by remember { mutableStateOf(1) } // 1, 2, 3 or finished
    var selectedBudget by remember { mutableStateOf<BudgetRange?>(null) }
    var selectedArea by remember { mutableStateOf<GeographicArea?>(null) }
    var selectedSkill by remember { mutableStateOf<SkillType?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Step indicator indicator in Malagasy
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BadgeStep(num = 1, active = step >= 1, label = "Tetibola")
            Spacer(modifier = Modifier.width(6.dp))
            Divider(modifier = Modifier.width(20.dp), color = if (step > 1) MadaGreen else Color.LightGray)
            Spacer(modifier = Modifier.width(6.dp))
            BadgeStep(num = 2, active = step >= 2, label = "Faritra")
            Spacer(modifier = Modifier.width(6.dp))
            Divider(modifier = Modifier.width(20.dp), color = if (step > 2) MadaGreen else Color.LightGray)
            Spacer(modifier = Modifier.width(6.dp))
            BadgeStep(num = 3, active = step >= 3, label = "Fahaizana")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Card displaying current question
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colors = CardDefaults.cardColors(containerColor = CardBg),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Step logic
                when (step) {
                    1 -> Column {
                        Text("Sivana Hikatsahana Tetikasa Mety", fontSize = 12.sp, color = MadaGreen, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("1. Ohatrinona ny tetibola azonao fampiasana hanombohana ?", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Charcoal)
                        Spacer(modifier = Modifier.height(16.dp))

                        BudgetRange.values().forEach { range ->
                            val isSelected = selectedBudget == range
                            SelectableOptionCard(
                                title = range.label,
                                subtitle = when(range) {
                                    BudgetRange.LOW -> "Mety tsara hanombohana irery any an-trano nefa tsy mila fahasahiranana be."
                                    BudgetRange.MEDIUM -> "Afaka mividy akora fototra madinika na fitaovana tsotra ho enti-manomboka."
                                    BudgetRange.HIGH -> "Ho an'ireo te hanangana fotodrafitrasa matanjaka sy fitaovana maoderina."
                                },
                                selected = isSelected,
                                onSelect = { selectedBudget = range }
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }

                    2 -> Column {
                        Text("Sivana Hikatsahana Tetikasa Mety", fontSize = 12.sp, color = MadaGreen, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("2. Aiza no faritra kasainao hanatanterahana ny asa ?", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Charcoal)
                        Spacer(modifier = Modifier.height(16.dp))

                        GeographicArea.values().forEach { area ->
                            val isSelected = selectedArea == area
                            SelectableOptionCard(
                                title = area.label,
                                subtitle = when(area) {
                                    GeographicArea.URBAN -> "Miompana amin'ny olona maro an-tanàn-dehibe, internet mavitrika, sy internet finday."
                                    GeographicArea.RURAL -> "Zava-dehibe amin'ny fambolena, fiompiana akoho na biby mampiasa hery natoraly."
                                    GeographicArea.BOTH -> "Azo atao na aiza na aiza, na an-tanàn-dehibe na any amin'ny faritany koa."
                                },
                                selected = isSelected,
                                onSelect = { selectedArea = area }
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }

                    3 -> Column {
                        Text("Sivana Hikatsahana Tetikasa Mety", fontSize = 12.sp, color = MadaGreen, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("3. Inona no karazana fahaizana anananao ?", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Charcoal)
                        Spacer(modifier = Modifier.height(16.dp))

                        SkillType.values().forEach { skill ->
                            val isSelected = selectedSkill == skill
                            SelectableOptionCard(
                                title = skill.label,
                                subtitle = when(skill) {
                                    SkillType.DIGITAL -> "Solon-tsaina, fanoratana, serasera amin'ny internet, varotra an-tserasera."
                                    SkillType.MANUAL -> "Varotra mivantana, asatanana, fitaterana, na fahaizan-tanana isan-karazany."
                                    SkillType.AGRICULTURAL -> "Famolena, fiompiana, fahafantarana ny toetrandro sy ny tany."
                                    SkillType.ANY -> "Tsy mila fahaizana manokana avy hatrany, fa azo ianarana tsikelikely eny am-panatanterahana."
                                },
                                selected = isSelected,
                                onSelect = { selectedSkill = skill }
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }

                    // step 4 = outputs matches
                    else -> Column(
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        Text("🎯 Ireo Hevitra Mety Aminao", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MadaGreen)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text("Ireto ny fandraharahana mifanaraka tsara amin'ny valin-teninao :", fontSize = 11.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(12.dp))

                        // Match calculations
                        val matches = BusinessRepository.ideas.filter { idea ->
                            // Budget match (either <= user budget or equal)
                            val budgetOk = if (selectedBudget != null) {
                                when (selectedBudget) {
                                    BudgetRange.LOW -> idea.budgetRange == BudgetRange.LOW
                                    BudgetRange.MEDIUM -> idea.budgetRange == BudgetRange.LOW || idea.budgetRange == BudgetRange.MEDIUM
                                    BudgetRange.HIGH -> true
                                    null -> true
                                }
                            } else true
                            
                            val areaOk = if (selectedArea != null) {
                                idea.geographicArea == GeographicArea.BOTH || idea.geographicArea == selectedArea
                            } else true

                            val skillOk = if (selectedSkill != null) {
                                idea.skillType == SkillType.ANY || idea.skillType == selectedSkill
                            } else true

                            budgetOk && areaOk && skillOk
                        }

                        if (matches.isEmpty()) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = SoftBg)
                            ) {
                                Column(modifier = Modifier.padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("Tsy nisy mifanaraka 100%", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    Text("Tsy misy hevitra mifanaraka 100% nefa ireto misy hevitra hafa azonao jerena (mety mila tetibola na fahaizana misimisy kokoa).", fontSize = 11.sp, textAlign = TextAlign.Center)
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Button(onClick = {
                                        onSelectIdea(BusinessRepository.ideas.first())
                                    }, colors = ButtonDefaults.buttonColors(containerColor = MadaGreen)) {
                                        Text("Hizaha ny hevitra sangany")
                                    }
                                }
                            }
                        } else {
                            matches.forEach { matchedIdea ->
                                MatchIdeaRow(matchedIdea, onOpen = { onSelectIdea(matchedIdea) })
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))
                        
                        Divider()
                        Spacer(modifier = Modifier.height(10.dp))

                        // Selected filters Recap
                        Text("⚙️ Ireo safidy nataonao :", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = Color.Gray)
                        Text("- Tetibola: ${selectedBudget?.label}", fontSize = 10.sp)
                        Text("- Faritra: ${selectedArea?.label}", fontSize = 10.sp)
                        Text("- Fahaizana: ${selectedSkill?.label}", fontSize = 10.sp)
                    }
                }

                // Control Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (step > 1) {
                        OutlinedButton(
                            onClick = {
                                if (step > 3) step = 3 else step--
                            },
                        ) {
                            Text("Hiverina", color = MadaGreen)
                        }
                    } else {
                        Spacer(modifier = Modifier.width(1.dp))
                    }

                    if (step < 4) {
                        val isEnable = when(step) {
                            1 -> selectedBudget != null
                            2 -> selectedArea != null
                            3 -> selectedSkill != null
                            else -> false
                        }
                        Button(
                            onClick = { step++ },
                            enabled = isEnable,
                            colors = ButtonDefaults.buttonColors(containerColor = MadaGreen)
                        ) {
                            Text("Manaraka")
                        }
                    } else {
                        Button(
                            onClick = {
                                // Reset step
                                step = 1
                                selectedBudget = null
                                selectedArea = null
                                selectedSkill = null
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MadaRed)
                        ) {
                            Text("Averina fanombohana")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BadgeStep(num: Int, active: Boolean, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(if (active) MadaGreen else Color.LightGray, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(num.toString(), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp)
        }
        Text(label, fontSize = 10.sp, color = if (active) Charcoal else Color.Gray, fontWeight = if (active) FontWeight.Bold else FontWeight.Normal)
    }
}

@Composable
fun SelectableOptionCard(
    title: String,
    subtitle: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() },
        colors = CardDefaults.cardColors(
            containerColor = if (selected) SoftGreen else CardBg
        ),
        border = if (selected) androidx.compose.foundation.BorderStroke(2.dp, MadaGreen) else null,
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selected,
                onClick = onSelect,
                colors = RadioButtonDefaults.colors(selectedColor = MadaGreen)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Charcoal)
                Text(subtitle, fontSize = 10.sp, color = Color.DarkGray, lineHeight = 13.sp)
            }
        }
    }
}

@Composable
fun MatchIdeaRow(idea: BusinessIdea, onOpen: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOpen() },
        colors = CardDefaults.cardColors(containerColor = SoftGreen.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(idea.sector, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = MadaGreen)
                Text(idea.title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Charcoal)
                Text("Renivola ilaina : ${formatAr(idea.averageStartupCostAr)}", fontSize = 10.sp, color = Color.Gray)
            }
            
            Button(
                onClick = onOpen,
                colors = ButtonDefaults.buttonColors(containerColor = MadaGreen),
                modifier = Modifier.height(28.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Hizaha", fontSize = 10.sp)
            }
        }
    }
}

// Tab 3: Formations / Small guide courses in Malagasy with checkbox read progression!
@Composable
fun TrainingScreen() {
    var readSectionsCount by remember { mutableStateOf(setOf<String>()) }
    var selectedModuleIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top score bar in Malagasy
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            colors = CardDefaults.cardColors(containerColor = CardBg),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            val totalSections = BusinessRepository.modules.sumOf { it.contentSections.size }.toFloat()
            val progressPercent = if (totalSections > 0) (readSectionsCount.size.toFloat() / totalSections * 100f).coerceAtMost(100f) else 0f
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("🎓 Ny Fandrosoanao amin'ny Fianarana", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = MadaGreen)
                    Text("${progressPercent.toInt()}% vita", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(6.dp))
                LinearProgressIndicator(
                    progress = { progressPercent / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = MadaGreen,
                    trackColor = Color.LightGray.copy(alpha = 0.4f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text("Hamafiso ny fizarana tamin'ny alalan'ny fanindriana ny 'Vakiana & Azoko' isaky ny fizarana.", fontSize = 10.sp, color = Color.Gray)
            }
        }

        if (selectedModuleIndex == -1) {
            // Lecture view list
            Text("📚 Ny Lohahevitra momba ny Fandraharahana :", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(BusinessRepository.modules) { module ->
                    val totalSub = module.contentSections.size
                    // Compute how many sub are read
                    val readInThisModule = module.contentSections.filter { sec ->
                        readSectionsCount.contains("${module.number}_${sec.subtitle}")
                    }.size

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedModuleIndex = module.number - 1 },
                        colors = CardDefaults.cardColors(containerColor = CardBg),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(SoftGreen, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "F${module.number}",
                                    fontWeight = FontWeight.Bold,
                                    color = MadaGreen,
                                    fontSize = 13.sp
                                )
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = module.title,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Charcoal
                                )
                                Text(
                                    text = module.summary,
                                    fontSize = 11.sp,
                                    color = Color.Gray,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = MadaGreen, modifier = Modifier.size(12.dp))
                                    Spacer(modifier = Modifier.width(2.dp))
                                    Text("~ ${module.durationMin} min", fontSize = 10.sp, color = Color.Gray)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text("$readInThisModule / $totalSub voavaky", fontSize = 10.sp, color = MadaGreen, fontWeight = FontWeight.Bold)
                                }
                            }

                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = MadaGreen
                            )
                        }
                    }
                }
            }
        } else {
            // Full Detailed View of selected module
            val activeModule = BusinessRepository.modules[selectedModuleIndex]
            
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(
                        onClick = { selectedModuleIndex = -1 },
                        colors = ButtonDefaults.textButtonColors(contentColor = MadaGreen)
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Hanaraka ny Lohahevitra", fontWeight = FontWeight.Bold)
                    }

                    Text("Fizarana ${activeModule.number} / ${BusinessRepository.modules.size}", fontSize = 11.sp, color = Color.Gray)
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = activeModule.title,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Charcoal
                )
                Text(
                    text = activeModule.summary,
                    fontSize = 11.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    activeModule.contentSections.forEachIndexed { subIdx, section ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = CardBg),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = section.subtitle,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = MadaGreen
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                section.bulletPoints.forEach { pt ->
                                    Row(
                                        modifier = Modifier.padding(vertical = 3.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Text("•", fontWeight = FontWeight.ExtraBold, color = MadaGreen)
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(text = pt, fontSize = 11.sp, lineHeight = 14.sp)
                                    }
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                // Madagascar Specific Warning
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = SoftGreen.copy(alpha = 0.5f)),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Column(modifier = Modifier.padding(10.dp)) {
                                        Text("🇲🇬 Ny zava-misy marina eto Madagasikara :", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MadaGreen)
                                        Text(section.malagasyContext, fontSize = 10.sp, color = Color.DarkGray, lineHeight = 13.sp)
                                    }
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                // Checkbox to mark as read
                                val sectionKey = "${activeModule.number}_${section.subtitle}"
                                val isRead = readSectionsCount.contains(sectionKey)

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(if (isRead) SoftGreen else SoftBg)
                                        .clickable {
                                            readSectionsCount = if (isRead) {
                                                readSectionsCount - sectionKey
                                            } else {
                                                readSectionsCount + sectionKey
                                            }
                                        }
                                        .padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = isRead,
                                        onCheckedChange = {
                                            readSectionsCount = if (isRead) {
                                                readSectionsCount - sectionKey
                                            } else {
                                                readSectionsCount + sectionKey
                                            }
                                        },
                                        colors = CheckboxDefaults.colors(checkedColor = MadaGreen)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = if (isRead) "Voavaky & Voamafy ✓" else "Asio marika hoe: Vakiana sady Azoko",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp,
                                        color = if (isRead) MadaGreen else Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// Tab 4: Simulator / Financial calculator in Malagasy
@Composable
fun SimulationScreen(
    initialCapital: String,
    initialPrice: String,
    initialCost: String,
    initialQty: String,
    initialFixed: String,
    projectTitle: String,
    onValuesChanged: (String, String, String, String, String, String) -> Unit
) {
    // Math Inputs
    var capitalInput by remember(initialCapital) { mutableStateOf(initialCapital) }
    var priceInput by remember(initialPrice) { mutableStateOf(initialPrice) }
    var costInput by remember(initialCost) { mutableStateOf(initialCost) }
    var qtyInput by remember(initialQty) { mutableStateOf(initialQty) }
    var fixedInput by remember(initialFixed) { mutableStateOf(initialFixed) }
    var titleInput by remember(projectTitle) { mutableStateOf(projectTitle) }

    // Fire notifications when changed
    LaunchedEffect(capitalInput, priceInput, costInput, qtyInput, fixedInput, titleInput) {
        onValuesChanged(capitalInput, priceInput, costInput, qtyInput, fixedInput, titleInput)
    }

    // Convert values safely
    val capValue = capitalInput.toLongOrNull() ?: 0L
    val priceValue = priceInput.toLongOrNull() ?: 0L
    val costValue = costInput.toLongOrNull() ?: 0L
    val qtyValue = qtyInput.toLongOrNull() ?: 0L
    val fixedValue = fixedInput.toLongOrNull() ?: 0L

    // Calculs
    val unitMargin = priceValue - costValue
    val monthlyRevenue = priceValue * qtyValue
    val monthlyCostOfSales = costValue * qtyValue
    val totalMonthlyExpenses = fixedValue + monthlyCostOfSales
    val monthlyNetProfit = monthlyRevenue - totalMonthlyExpenses

    val profitMarginPercent = if (monthlyRevenue > 0) {
        (monthlyNetProfit.toDouble() / monthlyRevenue.toDouble() * 100.0)
    } else 0.0

    // Seuil de rentabilité (Break even quant)
    val breakEvenQty = if (unitMargin > 0) {
        (fixedValue / unitMargin).coerceAtLeast(0)
    } else 0

    val breakEvenRevenue = breakEvenQty * priceValue

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Explanatory Intro Card in Malagasy
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = SoftGreen),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Info, contentDescription = null, tint = MadaGreen)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Fandinihana sy Kajy ny Tombam-bola", fontWeight = FontWeight.Bold, color = MadaGreen, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text("Kajio tsara ny vola miodina amin'ny orinasanao. Ampitomboy na ahenao ny vidin-javatra mba handrakofana ny fandaniana raikitra rehetra.", fontSize = 11.sp, color = Color.DarkGray)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Preset Templates Row in Malagasy
        Text("⚡ Santionany tetikasa azo alaina tahaka :", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            val templateOptions = listOf(
                Triple("Akoho Gasy", "800000", "15000"),
                Triple("Ranom-boankazo", "250000", "2500"),
                Triple("Friperie amin'ny fb", "600000", "8000")
            )

            templateOptions.forEach { t ->
                val labelShow = t.first
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            titleInput = t.first
                            capitalInput = t.second
                            priceInput = t.third
                            costInput = when (t.first) {
                                "Akoho Gasy" -> "8000"
                                "Ranom-boankazo" -> "1100"
                                "Friperie amin'ny fb" -> "3200"
                                else -> "5000"
                            }
                            qtyInput = when (t.first) {
                                "Akoho Gasy" -> "60"
                                "Ranom-boankazo" -> "350"
                                "Friperie amin'ny fb" -> "130"
                                else -> "100"
                            }
                            fixedInput = when (t.first) {
                                "Akoho Gasy" -> "50000"
                                "Ranom-boankazo" -> "35000"
                                "Friperie amin'ny fb" -> "60000"
                                else -> "40000"
                            }
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = if (titleInput == t.first) MadaGreen.copy(alpha = 0.12f) else CardBg
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Box(modifier = Modifier.padding(8.dp), contentAlignment = Alignment.Center) {
                        Text(labelShow, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Outputs Box in Malagasy
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = if (monthlyNetProfit > 0) SoftGreen else MadaRed.copy(alpha = 0.1f)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "VOKATRA ISAM-BOLANA (TOMBATOMBANA) :",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (monthlyNetProfit >= 0) "Tombony Madio" else "Fatiantoka Isam-bolana",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = if (monthlyNetProfit >= 0) MadaGreen else MadaRed
                    )
                    Text(
                        text = formatAr(monthlyNetProfit),
                        fontSize = 19.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = if (monthlyNetProfit >= 0) MadaGreen else MadaRed
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Tombony isaky ny Varotra iray (Marge) :",
                        fontSize = 11.sp,
                        color = Color.DarkGray
                    )
                    Text(
                        "${String.format("%.1f", profitMarginPercent)}%",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (monthlyNetProfit >= 0) MadaGreen else MadaRed
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                Divider(color = Color.Gray.copy(alpha = 0.3f))
                Spacer(modifier = Modifier.height(10.dp))

                // Break-Even Metrics in Malagasy
                Text(
                    text = "🎯 SEHETRA TSY MISY FATIANTOKA (Break-Even) :",
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = Charcoal
                )
                
                if (unitMargin <= 0) {
                    Text(
                        "⚠️ FITANDREMANA : Ambany loatra noho ny fandaniana fanaovana azy ny vidiny ivarotanao azy ! Matiantoka ianao isaky ny mivarotra entana. Ampitomboy ny vidiny !",
                        fontSize = 11.sp,
                        color = MadaRed,
                        lineHeight = 14.sp
                    )
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Isan'ny entana tsy maintsy lafo farafahakeliny :", fontSize = 11.sp, color = Color.DarkGray)
                        Text("$breakEvenQty entana / volana", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MadaGreen)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Vola handrakofana fotsiny ny vola maty :", fontSize = 11.sp, color = Color.DarkGray)
                        Text(formatAr(breakEvenRevenue), fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MadaGreen)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Input Fields Inside Card in Malagasy
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardBg),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("📝 Fampidirana ny Tarehimarika", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                Spacer(modifier = Modifier.height(8.dp))

                CustomTextInput(
                    label = "Anaran'ny tetikasanao",
                    value = titleInput,
                    onValueChange = { titleInput = it },
                    keyboardType = KeyboardType.Text,
                    suffix = ""
                )

                CustomTextInput(
                    label = "Renivola ilaina farafahakeliny",
                    value = capitalInput,
                    onValueChange = { capitalInput = it },
                    keyboardType = KeyboardType.Number,
                    suffix = "Ar"
                )

                CustomTextInput(
                    label = "Vidin'ny entana iray amidy",
                    value = priceInput,
                    onValueChange = { priceInput = it },
                    keyboardType = KeyboardType.Number,
                    suffix = "Ar"
                )

                CustomTextInput(
                    label = "Saran'ny akora fikarakarana entana iray",
                    value = costInput,
                    onValueChange = { costInput = it },
                    keyboardType = KeyboardType.Number,
                    suffix = "Ar"
                )

                CustomTextInput(
                    label = "Fandaniana raikitra isam-bolana (Internet, Hofan-trano, doro-lasantsy...)",
                    value = fixedInput,
                    onValueChange = { fixedInput = it },
                    keyboardType = KeyboardType.Number,
                    suffix = "Ar"
                )

                CustomTextInput(
                    label = "Isan'ny entana lafo isam-bolana (heverina)",
                    value = qtyInput,
                    onValueChange = { qtyInput = it },
                    keyboardType = KeyboardType.Number,
                    suffix = "entana"
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun CustomTextInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    suffix: String
) {
    Column(modifier = Modifier.padding(vertical = 5.dp)) {
        Text(label, fontSize = 11.sp, color = Color.Gray, fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            trailingIcon = {
                if (suffix.isNotEmpty()) {
                    Text(suffix, fontSize = 12.sp, color = MadaGreen, fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 6.dp))
                }
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MadaGreen,
                unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = SoftBg.copy(alpha = 0.5f),
                unfocusedContainerColor = SoftBg.copy(alpha = 0.2f)
            ),
            shape = RoundedCornerShape(8.dp)
        )
    }
}

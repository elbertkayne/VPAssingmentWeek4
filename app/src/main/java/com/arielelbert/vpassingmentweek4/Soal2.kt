package com.arielelbert.vpassingmentweek4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Hero(
    val id: Int,
    val name: String,
    val rarity: String,
    val role: String,
    val level: Int,
    val cp: Int,
    val hpCurrent: Int,
    val hpMax: Int,
    val mpCurrent: Int,
    val mpMax: Int,
    val atk: Int,
    val def: Int,
    val crit: Int,
    val spd: Int,
    val skillName: String,
    val skillDesc: String,
    val isInSquad: Boolean = false,
    val isExpanded: Boolean = false,
    val imageRes: Int
)

class Soal2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PartyRoster()
        }
    }
}

@Composable
fun PartyRoster() {
    val darkBackground = Color(0xFF0F1016)
    val cardBackground = Color(0xFF1A1C29)
    val goldAccent = Color(0xFFFFC107)
    val heroList = remember {
        mutableStateListOf(
            Hero(1, "Alistair", "MYTHIC", "High Templar", 50, 5600, 1650, 1800, 650, 800, 380, 540, 22, 105, "Wrath of the Heavens", "Stuns all enemies for 3 seconds.", imageRes = R.drawable.prajurit),
            Hero(2, "Theresa", "MYTHIC", "Divine Oracle", 49, 5480, 1100, 1200, 2100, 2400, 310, 420, 18, 110, "Divine Light", "Heals allies and grants a protective shield.", imageRes = R.drawable.girl),
            Hero(3, "Vespera", "MYTHIC", "Shadowblade", 48, 5350, 890, 950, 410, 600, 450, 300, 35, 130, "Shadow Strike", "Strikes with critical precision.", imageRes = R.drawable.idk),
            Hero(4, "Elaria", "MYTHIC", "Archmage", 47, 5120, 450, 600, 1900, 2000, 520, 250, 25, 95, "Meteor Fall", "Summons a massive meteor dealing AOE damage.", isInSquad = true, imageRes = R.drawable.elaria),
            Hero(5, "Balthazar", "EPIC", "Berserker", 45, 4800, 1500, 1600, 200, 400, 410, 320, 20, 100, "Raging Slash", "Unleashes a fierce slash of fury.", imageRes = R.drawable.prajurit2),
            Hero(6, "Kaelen", "EPIC", "Archmage", 44, 4650, 600, 750, 1500, 1800, 480, 210, 18, 90, "Frost Nova", "Freezes nearby enemies in place.", imageRes = R.drawable.elaria),
            Hero(7, "Thorne", "RARE", "Berserker", 42, 4300, 1300, 1400, 150, 300, 390, 290, 15, 95, "Earthquake", "Slams the ground shaking foes.", imageRes = R.drawable.prajurit),
            Hero(8, "Lyra", "EPIC", "Divine Oracle", 43, 4400, 950, 1100, 1700, 1900, 280, 350, 12, 105, "Blessing", "Increases team defense temporarily.", imageRes = R.drawable.sans),
            Hero(9, "Zephyr", "MYTHIC", "Shadowblade", 46, 5200, 820, 900, 500, 700, 460, 280, 40, 140, "Wind Slash", "Swift attack bypassing enemy armor.", imageRes = R.drawable.vespera),
            Hero(10, "Valen", "RARE", "Vanguard", 40, 4100, 1700, 2000, 100, 200, 300, 500, 10, 85, "Shield Wall", "Blocks incoming frontal attacks.", imageRes = R.drawable.elaria),
            Hero(11, "Cassie", "EPIC", "Archer", 43, 4550, 750, 850, 800, 1000, 430, 220, 30, 120, "Snipe", "Fires a high-damage piercing arrow.", imageRes = R.drawable.vespera),
            Hero(12, "Darius", "RARE", "Berserker", 39, 3950, 1200, 1350, 120, 250, 370, 270, 18, 92, "Bloodlust", "Increases attack speed temporarily.", imageRes = R.drawable.idk),
            Hero(13, "Selene", "EPIC", "Archmage", 44, 4700, 550, 700, 1600, 1900, 490, 230, 22, 98, "Chain Lightning", "Discharges lightning jumping between targets.", imageRes = R.drawable.elaria),
            Hero(14, "Orin", "RARE", "Divine Oracle", 41, 4200, 900, 1050, 1400, 1600, 260, 330, 15, 102, "Minor Heal", "Restores health to the lowest HP ally.", imageRes = R.drawable.prajurit)
        )
    }

    var selectedFilter by remember { mutableStateOf("All (14)") }
    val filters = listOf("All (14)", "Berserker (2)", "Archmage (3)", "Divine (4)", "Support (3)")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = darkBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Party Roster",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Guild Vanguard • 14 Heroes Available",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFF2A2518)
                ) {
                    Text(
                        text = "★ Expedition",
                        fontSize = 12.sp,
                        color = goldAccent,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = cardBackground),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "EXPEDITION SQUAD (3/4)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Green
                        )
                        Text(
                            text = "⚡ 14750 CP",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = goldAccent
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val squadImages = listOf(
                            R.drawable.prajurit,
                            R.drawable.girl,
                            R.drawable.idk)

                        squadImages.forEach { imageRes ->
                            Box(
                                modifier = Modifier
                                    .size(45.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.DarkGray)
                            ) {
                                Image(
                                    painter = painterResource(id = imageRes),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .size(45.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF252836)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "+", fontSize = 18.sp, color = Color.Gray)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Search by hero name, class, or rarity...", fontSize = 12.sp, color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = cardBackground,
                    focusedContainerColor = cardBackground,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = goldAccent
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                filters.forEach { filter ->
                    val isSelected = filter == selectedFilter
                    Button(
                        onClick = { selectedFilter = filter },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) goldAccent else cardBackground
                        ),
                        shape = RoundedCornerShape(20.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = filter,
                            fontSize = 12.sp,
                            color = if (isSelected) Color.Black else Color.White,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(heroList) { hero ->
                    HeroCard(
                        hero = hero,
                        onCardClick = {
                            val index = heroList.indexOf(hero)
                            if (index != -1) {
                                heroList[index] = hero.copy(isExpanded = !hero.isExpanded)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HeroCard(
    hero: Hero,
    onCardClick: () -> Unit
) {
    val cardBackground = Color(0xFF1A1C29)
    val goldAccent = Color(0xFFFFC107)

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackground),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick() }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = Color(0xFF4A1525)
                    ) {
                        Text(
                            text = hero.rarity,
                            fontSize = 9.sp,
                            color = Color(0xFFFF8A8A),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = Color(0xFF2D2A18)
                    ) {
                        Text(
                            text = hero.role,
                            fontSize = 9.sp,
                            color = goldAccent,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    if (hero.isInSquad) {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = Color(0xFF182D24)
                        ) {
                            Text(
                                text = "IN SQUAD",
                                fontSize = 9.sp,
                                color = Color.Green,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "⚡ ${hero.cp} CP",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = if (hero.isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "Expand",
                        tint = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.DarkGray)
                ) {
                    Image(
                        painter = painterResource(id = hero.imageRes),
                        contentDescription = hero.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .background(Color.Black.copy(alpha = 0.7f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Lvl ${hero.level}",
                            fontSize = 9.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = hero.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "HP", fontSize = 10.sp, color = Color.Gray)
                        Text(text = "${hero.hpCurrent} / ${hero.hpMax}", fontSize = 10.sp, color = Color.Gray)
                    }
                    LinearProgressIndicator(
                        progress = { hero.hpCurrent.toFloat() / hero.hpMax.toFloat() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = Color.Red,
                        trackColor = Color.DarkGray,
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "MP", fontSize = 10.sp, color = Color.Gray)
                        Text(text = "${hero.mpCurrent} / ${hero.mpMax}", fontSize = 10.sp, color = Color.Gray)
                    }
                    LinearProgressIndicator(
                        progress = { hero.mpCurrent.toFloat() / hero.mpMax.toFloat() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = Color.Blue,
                        trackColor = Color.DarkGray,
                    )
                }
            }

            if (hero.isExpanded) {
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = Color.DarkGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    StatBox("ATK", hero.atk.toString())
                    StatBox("DEF", hero.def.toString())
                    StatBox("CRIT", "${hero.crit}%")
                    StatBox("SPD", hero.spd.toString())
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "★ ${hero.skillName}", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = goldAccent)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = hero.skillDesc, fontSize = 11.sp, color = Color.LightGray)

                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Heal (+250 HP)", fontSize = 11.sp, color = Color.Green)
                    }
                    Button(
                        onClick = { },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = goldAccent),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Add to Squad", fontSize = 11.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun StatBox(label: String, value: String) {
    Column(
        modifier = Modifier
            .background(Color(0xFF222533), shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = label, fontSize = 10.sp, color = Color.Gray)
        Text(text = value, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
    }
}

@Preview(showBackground = true, name = "Party Roster - Default State")
@Composable
fun PartyRosterPreview() {
    PartyRoster()
}
package com.arielelbert.vpassingmentweek4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Car(
    val id: Int,
    val name: String,
    val transmission: String,
    val fuelType: String,
    val year: Int,
    val mileage: String,
    val price: String,
    val description: String,
    val location: String,
    val imageRes: Int,
    var isFavorite: Boolean = false
)

class Soal1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarSale()
        }
    }
}

@Composable
fun CarSale() {
    val carList = remember {
        mutableStateListOf(
            Car(1, "Tiggo 9 CSH", "Auto", "PHEV", 2024, "5,000 km", "Rp 450.000.000", "The Tiggo 9 CSH is a premium SUV with cutting-edge PHEV technology.", "Jakarta", R.drawable.tiggo_9_csh),
            Car(2, "Honda CRV", "Auto", "PETROL", 2013, "15,200 km", "Rp 280.000.000", "Reliable and spacious family SUV known for comfort and durability.", "Bandung", R.drawable.honda_crv),
            Car(3, "Toyota Yaris Cross", "Auto", "HYBRID", 2024, "30,500 km", "Rp 350.000.000", "Compact hybrid crossover offering excellent fuel efficiency.", "Surabaya", R.drawable.toyota_yaris_cross),
            Car(4, "Daihatsu Xenia", "Manual", "PETROL", 2022, "28,000 km", "Rp 210.000.000", "The ultimate family MPV, practical and economical.", "Yogyakarta", R.drawable.daihatsu_xenia),
            Car(5, "Toyota Yaris", "Auto", "PETROL", 2021, "52,400 km", "Rp 245.000.000", "The Toyota Yaris G AT is a sporty compact hatchback that punches above its weight class. Equipped with a 1.5L dual VVT-i engine, 7 airbags, pre-collision system, and a floating head unit.", "Semarang", R.drawable.toyota_yaris)
        )
    }

    var selectedCar by remember { mutableStateOf<Car?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (selectedCar == null) {
            MainScreen(
                cars = carList,
                onCarClick = { car -> selectedCar = car },
                onFavoriteToggle = { car ->
                    val index = carList.indexOf(car)
                    if (index != -1) {
                        carList[index] = car.copy(isFavorite = !car.isFavorite)
                    }
                }
            )
        } else {
            DetailScreen(
                car = selectedCar!!,
                onBackClick = { selectedCar = null }
            )
        }
    }
}

@Composable
fun MainScreen(
    cars: List<Car>,
    onCarClick: (Car) -> Unit,
    onFavoriteToggle: (Car) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "On Sale",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Find your perfect ride",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFFFEEEE)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Saved",
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "5 saved",
                        fontSize = 12.sp,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(cars) { car ->
                CarCard(
                    car = car,
                    onCardClick = { onCarClick(car) },
                    onFavoriteClick = { onFavoriteToggle(car) }
                )
            }
        }
    }
}

@Composable
fun CarCard(
    car: Car,
    onCardClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = car.imageRes),
                    contentDescription = car.name,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = car.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    IconButton(
                        onClick = onFavoriteClick,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = if (car.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (car.isFavorite) Color.Red else Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = Color(0xFFF0F0F0)
                    ) {
                        Text(
                            text = car.transmission,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = Color(0xFFFFF0E0)
                    ) {
                        Text(
                            text = car.fuelType,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "📅 ${car.year}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = car.mileage,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun DetailScreen(
    car: Car,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = onBackClick) {
            Text(text = "← Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = car.imageRes),
                contentDescription = car.name,
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = car.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = car.location,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = car.price,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFD32F2F),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "SPECIFICATIONS",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "YEAR", fontSize = 12.sp, color = Color.Gray)
                Text(text = car.year.toString(), fontWeight = FontWeight.Bold)
            }
            Column {
                Text(text = "MILEAGE", fontSize = 12.sp, color = Color.Gray)
                Text(text = car.mileage, fontWeight = FontWeight.Bold)
            }
            Column {
                Text(text = "TRANSMISSION", fontSize = 12.sp, color = Color.Gray)
                Text(text = car.transmission, fontWeight = FontWeight.Bold)
            }
            Column {
                Text(text = "FUEL TYPE", fontSize = 12.sp, color = Color.Gray)
                Text(text = car.fuelType, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "ABOUT THIS CAR",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Text(
            text = car.description,
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
        ) {
            Text(text = "Contact Seller", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CarAppPreview() {
    CarSale()
}

@Preview(showBackground = true, name = "Detail Screen Preview")
@Composable
fun DetailScreenPreview() {
    val sampleCar = Car(id = 5, name = "Toyota Yaris", transmission = "Auto", fuelType = "PETROL", year = 2021, mileage = "52,400 km", price = "Rp 245.000.000", description = "The Toyota Yaris G AT is a sporty compact hatchback that punches above its weight class. Equipped with a 1.5L dual VVT-i engine, 7 airbags, pre-collision system, and a floating head unit, it offers premium features at a very accessible price point.", location = "Semarang", imageRes = R.drawable.toyota_yaris
    )
    DetailScreen(
        car = sampleCar,
        onBackClick = {}
    )
}
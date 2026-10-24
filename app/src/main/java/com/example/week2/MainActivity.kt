
package com.example.week2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                DevClusterScreen()
            }
        }
    }
}

@Composable
fun DevClusterScreen() {

    var searchQuery by rememberSaveable {
        mutableStateOf("")
    }

    var selectedCategory by remember {
        mutableStateOf("Tümü")
    }

    var totalSpent by remember {
        mutableStateOf(0.0)
    }

    var selectedCounts by remember {
        mutableStateOf<Map<String, Int>>(emptyMap())
    }

    val categories = remember {
        listOf("Tümü") +
                equipmentList.map { it.category }.distinct()
    }

    val filteredEquipment = remember(
        searchQuery,
        selectedCategory
    ) {
        equipmentList.filter { equipment ->

            val matchesSearch =
                equipment.name.contains(
                    searchQuery,
                    ignoreCase = true
                ) ||
                        equipment.category.contains(
                            searchQuery,
                            ignoreCase = true
                        )

            val matchesCategory =
                selectedCategory == "Tümü" ||
                        equipment.category == selectedCategory

            matchesSearch && matchesCategory
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "DevCluster",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Donanım ve kaynak yönetimi",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Toplam harcama: ${
                    String.format(Locale.US, "%.2f", totalSpent)
                }",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            HardwareSearchBar(
                query = searchQuery,
                onQueryChange = { newValue ->
                    searchQuery = newValue
                }
            )
        }

        CategorySelector(
            categories = categories,
            selected = selectedCategory,
            onCategoryClick = { category ->
                selectedCategory = category
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${filteredEquipment.size} donanım listeleniyor",
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = filteredEquipment,
                key = { it.id }
            ) { equipment ->

                val purchased =
                    selectedCounts[equipment.id] ?: 0

                val remaining =
                    equipment.stockCount - purchased

                HardwareCard(
                    item = equipment,
                    remainingStock = remaining,
                    onPurchase = {
                        if (remaining > 0) {
                            selectedCounts =
                                selectedCounts + (
                                        equipment.id to (purchased + 1)
                                        )

                            totalSpent += equipment.cost
                        }
                    }
                )
            }

            if (filteredEquipment.isEmpty()) {
                item {
                    Text(
                        text = "Aradığınız donanım bulunamadı."
                    )
                }
            }
        }
    }
}

@Composable
fun HardwareSearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        label = {
            Text("Donanım ara")
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun CategorySelector(
    categories: List<String>,
    selected: String,
    onCategoryClick: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            FilterChip(
                selected = category == selected,
                onClick = {
                    onCategoryClick(category)
                },
                label = {
                    Text(category)
                }
            )
        }
    }
}

@Composable
fun HardwareCard(
    item: HardwareItem,
    remainingStock: Int,
    onPurchase: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = remainingStock > 0,
                onClick = onPurchase
            )
            .padding(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = item.category,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Birim maliyet: ${
                    String.format(Locale.US, "%.2f", item.cost)
                }"
            )

            Text(
                text = if (remainingStock > 0) {
                    "Kalan stok: $remainingStock"
                } else {
                    "Stokta yok"
                },
                color = if (remainingStock > 0) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.error
                }
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = if (remainingStock > 0) {
                    "Satın almak için karta dokun"
                } else {
                    "Bu donanım seçilemez"
                },
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

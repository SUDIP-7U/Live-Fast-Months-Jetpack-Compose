package com.example.livefast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.YearMonth
import java.time.format.DateTimeFormatter


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //SingleMonthDropdown()
            February2026Calendar()

        }
    }
}

@Composable
fun YearMonthDropdown() {
    // Generate list of months from Jan 2024 to Dec 2026
    val months = generateYearMonthList(2024, 2026)
    var expanded by remember { mutableStateOf(false) }
    var selectedMonth by remember { mutableStateOf(months.first()) }

    Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        // Dropdown trigger
        OutlinedButton(onClick = { expanded = true }) {
            Text(selectedMonth)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.heightIn(max = 300.dp) // scrollable limit
        ) {
            months.forEach { month ->
                DropdownMenuItem(
                    text = { Text(month) },
                    onClick = {
                        selectedMonth = month
                        expanded = false
                    }
                )
            }
        }
    }
}

// Helper function to generate Year-Month list
fun generateYearMonthList(startYear: Int, endYear: Int): List<String> {
    val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
    val list = mutableListOf<String>()
    var date = YearMonth.of(startYear, 1) // January of startYear
    val endDate = YearMonth.of(endYear, 12) // December of endYear

    while (!date.isAfter(endDate)) {
        list.add(date.format(formatter))
        date = date.plusMonths(1)
    }
    return list
}

@Composable
fun SingleMonthDropdown() {
    var expanded by remember { mutableStateOf(false) }
    var selectedMonth by remember { mutableStateOf("February 2026") }

    Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        OutlinedButton(onClick = { expanded = true }) {
            Text(selectedMonth)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("February 2026") },
                onClick = {
                    selectedMonth = "February 2026"
                    expanded = false
                }
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun February2026Calendar() {
    val daysInMonth = 28
    val days = (1..daysInMonth).map { it.toString() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "February 2026",
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(7), // ✅ use columns instead of cells
            modifier = Modifier.fillMaxWidth()
        ) {
            items(days.size) { index ->
                Box(
                    modifier = Modifier
                        .aspectRatio(1f) // square cells
                        .padding(4.dp)
                        .background(Color(0xFFE4E6EB), shape = RoundedCornerShape(6.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = days[index],
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
        }
    }
}

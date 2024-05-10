package presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.model.WordItemDTO

@Composable
fun SearchedItem(
    item: WordItemDTO,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .clickable { onClick(item.id) }
            .padding(16.dp)
    ) {
        Text(
            text = item.word ?: "",
            style = MaterialTheme.typography.headlineSmall
        )
        item.phonetic?.let {
            Text(
                text = it,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif
                )
            )
        }
    }
}
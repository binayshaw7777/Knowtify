package presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun DefinitionItem(
    firstWord: String,
    secondWord: String,
    firstWordStyle: TextStyle = TextStyle(),
    secondWordStyle: TextStyle = TextStyle()
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = firstWord,
            style = firstWordStyle
        )
        Text(
            text = secondWord,
            style = secondWordStyle
        )
    }
}
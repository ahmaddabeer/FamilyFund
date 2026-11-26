package com.example.familyfund.presentation.member.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.familyfund.data.model.PaymentRequestModel
import com.example.familyfund.presentation.admin.screen.BoldFont
import com.example.familyfund.presentation.admin.screen.MediumFont
import com.example.familyfund.presentation.admin.screen.PrimaryColor
import com.example.familyfund.presentation.admin.screen.PrimaryTextColor
import com.example.familyfund.presentation.admin.screen.SecondaryTextColor
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
 fun TransactionCard(
     state: PaymentRequestModel
 ) {


    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    "Monthly Contribution",
                    fontFamily = BoldFont,
                    color = PrimaryTextColor,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    DateFormatter(state.createdAt),
                    fontFamily = MediumFont,
                    color = SecondaryTextColor,
                    style = MaterialTheme.typography.bodySmall
                )

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(0xFFDCFCE7)
                ) {
                    Text(
                        state.status,
                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 4.dp
                        ),
                        color = Color(0xFF15803D),
                        fontFamily = MediumFont,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            Text(
                state.amount.toString(),
                fontFamily = BoldFont,
                color = PrimaryColor,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }

}


private fun DateFormatter(date:Long):String{

    return SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(date))
}
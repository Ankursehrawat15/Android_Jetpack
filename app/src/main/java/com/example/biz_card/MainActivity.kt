package com.example.biz_card

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.biz_card.ui.theme.BizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Biz_Card()
                }
            }
        }
    }
}

@Composable
fun Biz_Card(){

    val isButtonClicked = remember{
        mutableStateOf(false)
    }

    Surface(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            elevation = 7.dp,
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
             backgroundColor = Color.White){

            Column(modifier =  Modifier.height(300.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
                Create_Profile()
                Divider(color = Color.Gray,
                thickness = 2.dp)
                CreateInfo()
                Button(onClick = {

                    isButtonClicked.value = !isButtonClicked.value // toggele back and forth

                }) {
                    Text(text = "Portfolio" , style = MaterialTheme.typography.button)
                    
                }

                if(isButtonClicked.value){
                    content()
                }else{
                    Box() {
                        
                    }
                }

            }

        }
    }
}

@Preview
@Composable
fun content(){
    
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(5.dp)) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp , color = Color.LightGray)
        ) {

            Portfolio(data = listOf("Project 1" , "Project 2" , "Project 3" , "Project 4"))

        }
        
    }

}

// list of the projects
@Composable
fun Portfolio(data: List<String>) {
   LazyColumn { 
       items(data){
           item ->

           Card(modifier = Modifier
               .padding(13.dp)
               .fillMaxWidth(),
               shape = RectangleShape,
               elevation = 4.dp
           ) {
               Row(modifier = Modifier
                   .padding(8.dp)
                   .background(MaterialTheme.colors.surface)
                   .padding(7.dp)) {
                   
                   Create_Profile(modifier = Modifier.size(100.dp))
                   
                       Column(modifier = Modifier.padding(7.dp).align(alignment = Alignment.CenterVertically)) {
                       Text(text = item , fontWeight = FontWeight.Bold)
                       Text(text = "A great Project" , fontWeight = FontWeight.Bold ,style = MaterialTheme.typography.body2)
                   }
                   
                   

               }

           }

       }
   }



}

// Adding info about the programmer
@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            "Ankur Sehrawat",
            color = Color.Blue,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Android compose programmer",
            modifier = Modifier.padding(3.dp)
        )
        Text(
            text = "@Ankursehrawat15",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1
        )
    }
}

// building profile Image-section
@Composable
private fun Create_Profile(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.DarkGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {

        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "Profile picture",
            modifier = modifier.size(135.dp)
        )


    }
}


// @Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizCardTheme {
       Biz_Card()
    }
}
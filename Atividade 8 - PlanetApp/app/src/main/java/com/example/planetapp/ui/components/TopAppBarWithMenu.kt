import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.planetapp.R


@ExperimentalMaterial3Api
@Composable
fun TopAppBarWithMenu(
   onSettingsClick: () -> Unit,
   onHelpClick: () -> Unit
) {
   var expanded by remember { mutableStateOf(false) }

   TopAppBar(
       title = {
           Row(verticalAlignment = Alignment.Top) {
               Image(
                   painter = painterResource(id = R.drawable.ic_launcher_foreground),
                   contentDescription = "App Logo",
                   modifier = Modifier
                       .size(30.dp)
                       .padding(end = 8.dp)
               )
               Text(
                   text = "PlanetApp",
                   style = MaterialTheme.typography.titleLarge,
                   maxLines = 1,
                   overflow = TextOverflow.Ellipsis
               )
           }
       },actions = {
           IconButton(onClick = { expanded = true }) {
               Icon(
                   imageVector = Icons.Default.MoreVert,
                   contentDescription = "Menu"
               )
           }
           DropdownMenu(
               expanded = expanded,
               onDismissRequest = { expanded = false }
           ) {
               DropdownMenuItem(
                   text = { Text("Configurações") },
                   onClick = {
                       expanded = false
                       onSettingsClick()
                   }
               )
               DropdownMenuItem(
                   text = { Text("Ajuda") },
                   onClick = {
                       expanded = false
                       onHelpClick()
                   }
               )
           }
       },
       colors = TopAppBarDefaults.topAppBarColors(
           containerColor = MaterialTheme.colorScheme.primary,
           titleContentColor = MaterialTheme.colorScheme.onPrimary,
           actionIconContentColor = MaterialTheme.colorScheme.onPrimary
       )
   )
}
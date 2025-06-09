package com.example.eat

import android.R.attr.onClick
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.Manifest
import android.util.Log
import android.view.View
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.tooling.preview.Preview // Убедитесь, что импорты для @Preview есть
import androidx.navigation.NavController // Импорт NavController
import androidx.navigation.compose.currentBackStackEntryAsState // Импорт для получения текущего маршрута
import androidx.compose.material.icons.filled.Map // Импорт для иконки Map
import androidx.compose.material.icons.filled.Person // Импорт для иконки Person
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.animation.AnimatedVisibility
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.PathEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialShapes.Companion
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable // Возможно, уже есть
import androidx.compose.ui.platform.LocalDensity // <-- Для перевода dp в px
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.*
import androidx.compose.material3.toShape
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.*

// Импорты Osmdroid
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.*

import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Aod
import androidx.compose.runtime.*
import android.content.Context.MODE_PRIVATE

import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.animation.PathInterpolator
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

import com.example.eat.ui.theme.EatTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScr()
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BackgroundCompose() {
    //sha[e
    val shape = Companion.Cookie12Sided.normalized().toShape()
    //fonts_by_google
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    //FontFamily
    val josefont = FontFamily(
        Font(
            googleFont = GoogleFont("Jose"),
            fontProvider = provider,
            //weight = FontWeight.
        )
    )
    Box(modifier = Modifier
        .fillMaxSize()
        .requiredWidth((currentWindowSize().width).dp)
        .background(color = MaterialTheme.colorScheme.tertiaryContainer),
        contentAlignment = Alignment.TopCenter

    ) {

        //arrow
       Spacer(Modifier.size(180.dp))
        Icon(
            painter = painterResource(R.drawable.star),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(20.dp)
                .offset(y =90.dp,x = 5.dp)

        )
        //additional parts
        Icon(
            painter = painterResource(R.drawable.meme),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(50.dp)
                .offset(y =65.dp,x = 155.dp)

        )
        Text(fontFamily = josefont, text = "Вкусныи выбор",
            style = TextStyle(fontWeight = FontWeight(800)),
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            fontSize = 35.sp,
            modifier =Modifier
                .offset(y = 100.dp)

        )
    }

}
data class CarouselItem(
    val id: Int,
    val imageResId: Int, // @DrawableRes val imageResId: Int
    val contentDescription: String,
    val title: String, // Добавим заголовок для модального окна
    val description: String // Добавим описание для модального окна
)
@Composable
fun CarouselPopup(
    item: CarouselItem,
    onDismiss: () -> Unit
) {
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    // Если используете свой шрифт, например:
    val josefont = FontFamily(Font(googleFont = GoogleFont("Jose"), fontProvider = provider))
    // Тогда используйте fontFamily = josefont в Text Composable
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(30.dp))
            .padding(24.dp)

            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.contentDescription,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                fontFamily = josefont,
                text = item.title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                fontFamily = josefont,
                text = item.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselExample_MultiBrowse(onItemClick: (CarouselItem) -> Unit) { // Добавлен колбэк
    val items = remember {
        listOf(
            CarouselItem(0, R.drawable.logo, "cupcake", "Вкусный Выбор", "Добро пожаловать в наше приложение с выгодными акциями и промокодами."),
            CarouselItem(1, R.drawable.burger_whopper, "donut", "Воппер", "Легендарный бургер с говядиной за пол цены."),
            CarouselItem(2, R.drawable.img_20250317_073535, "eclair", "БЕЗУМНАЯ СРЕДА", "2 порции терияки байтсов по цене 1."),
            CarouselItem(3, R.drawable.big_speshial, "oioioi", "Биг Спешал", "20% на лучший бургер всех времён.")
        )
    }

    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { items.count() },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 20.dp, bottom = 10.dp),
        preferredItemWidth = 200.dp,
        itemSpacing = 4.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { i ->
        val item = items[i]
        Image(
            modifier = Modifier
                .height(200.dp)
                .maskClip(MaterialTheme.shapes.extraLarge)
                .clickable { onItemClick(item) }, // Добавлен clickable для вызова колбэка
            painter = painterResource(id = item.imageResId),
            contentDescription = item.contentDescription,
            contentScale = ContentScale.Crop
        )
    }
}
data class RestaurantItem(
    val id: String, // Добавим уникальный ID для идентификации
    val name: String,
    val lastOrderInfo: String,
    val logoResId: Int, // Resource ID для логотипа
    val category: String, // Добавим категорию
    val locations: List<GeoPoint>
)

@OptIn(ExperimentalFoundationApi::class) // Нужен для combinedClickable
@Composable
fun RestaurantCard(
    item: RestaurantItem,
    shape: Shape,
    onClick: (RestaurantItem) -> Unit, // Для обычного клика (открытие модального окна)
    onFavoriteToggle: (RestaurantItem) -> Unit, // Для долгого нажатия (переключение избранного)
    isFavorite: Boolean // Является ли ресторан избранным

) {
    val fixedCardColor = MaterialTheme.colorScheme.primary
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(shape)
            .combinedClickable(
                onClick = { onClick(item) },
                onLongClick = { onFavoriteToggle(item) }
            ),
        colors = CardDefaults.cardColors(
            containerColor = fixedCardColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.logoResId),
                contentDescription = "${item.name} logo",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = item.lastOrderInfo,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            // Иконка "Избранное"
            // Теперь иконка просто отображает состояние, она сама по себе не кликабельна.
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = if (isFavorite) "Удалить из избранного" else "Добавить в избранное",
                tint = if (isFavorite) Color.Red else Color.White,
                modifier = Modifier
                    .size(15.dp)
                // Удаляем .clickable {} отсюда, так как переключение происходит по long press всей карточки
            )
        }
    }
}



@Composable
fun RestaurantCardsList(
    restaurants: List<RestaurantItem>,
    onCardClick: (RestaurantItem) -> Unit,
    onFavoriteToggle: (RestaurantItem) -> Unit,
    isRestaurantFavorite: (RestaurantItem) -> Boolean
) {
    val cornerRadius = 20.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        if (restaurants.isEmpty()) {
            Text(
                "Нет ресторанов для отображения",
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            restaurants.forEachIndexed { index, item ->
                val shape = when  {
                    restaurants.size == 1 -> RoundedCornerShape(cornerRadius)
                    index == 0 -> RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius, bottomStart = 0.dp, bottomEnd = 0.dp)
                    index == restaurants.lastIndex -> RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = cornerRadius, bottomEnd = cornerRadius)
                    else -> RectangleShape
                }

                if (index > 0) {
                    Spacer(modifier = Modifier.height(2.dp))
                }

                RestaurantCard(
                    item = item,
                    shape = shape,
                    onClick = onCardClick, // Передаем общий обработчик клика по карточке
                    onFavoriteToggle = onFavoriteToggle, // Передаем обработчик для избранного
                    isFavorite = isRestaurantFavorite(item) // Передаем состояние избранного
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainClickable(
    onCardClick: (RestaurantItem) -> Unit,
    onCarouselItemClick: (CarouselItem) -> Unit,
    favoriteRestaurantIds: SnapshotStateList<String>, // <-- Принимаем как параметр
    onFavoriteToggle: (RestaurantItem) -> Unit,
    onShowMap: () -> Unit
) {
    var selectedTab by remember { mutableStateOf("Все") }

 //   val favoriteRestaurantIds = remember { mutableStateListOf<String>() } // Храним ID избранных ресторанов

    val isRestaurantFavorite: (RestaurantItem) -> Boolean = { restaurant ->
        favoriteRestaurantIds.contains(restaurant.id)
    }
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    //FontFamily
    val josefont = FontFamily(
        Font(
            googleFont = GoogleFont("Jose"),
            fontProvider = provider,
        )
    )
    Box(
        modifier = Modifier
            .size(
                (currentWindowSize().height / 3 + 350).dp
            )
            .offset(y = 130.dp)
            .background(MaterialTheme.colorScheme.surfaceContainerLow), // Используем .background() для Box
        contentAlignment = Alignment.TopCenter
    ) {
        Column() {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    fontFamily = josefont,
                    text = "Лучшие Предложения",
                    style = TextStyle(fontWeight = FontWeight(800)),// Может, текст поменьше для маленького круга
                    fontSize = 26.sp,
                    // Настройте
                    color = MaterialTheme.colorScheme.tertiary
                )
                Icon(
                    painter = painterResource(R.drawable.better),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(100.dp)
                        .height(70.dp)
                        .offset(x = -90.dp, y = 30.dp)
                )
            }
            //Carousel


            CarouselExample_MultiBrowse(onItemClick = onCarouselItemClick)
            //
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(height = 5.dp, width = 100.dp)

                ) {}
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    fontFamily = josefont,
                    text = "Ваши рестораны",
                    style = TextStyle(fontWeight = FontWeight(800)),// Может, текст поменьше для маленького круга
                    fontSize = 26.sp,
                    // Настройте
                    color = MaterialTheme.colorScheme.tertiary
                )
                Icon(
                    painter = painterResource(R.drawable.restor),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(height = 70.dp, width = 170.dp)
                        .offset(x = 50.dp, y = 30.dp)
                )

            }
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TabButton(
                    text = "Все",
                    isSelected = selectedTab == "Все",
                    fontFamily = josefont, // Передаем fontFamily
                    onClick = { selectedTab = "Все" }
                )
                TabButton(
                    text = "Избранное",
                    isSelected = selectedTab == "Избранное",
                    fontFamily = josefont, // Передаем fontFamily
                    onClick = { selectedTab = "Избранное" }
                )
            }
            Spacer(Modifier.height(20.dp)) // Дополнительный отступ после вкладок

            // Список всех ресторанов (полный, неизменяемый)
            val allRestaurantsInMainClickable = remember {
                listOf(
                    RestaurantItem(
                        id = "vkusno_i_tochka",
                        name = "Вкусно и точка",
                        lastOrderInfo = "Последний заказ • неделю назад",
                        logoResId = R.drawable.vkus_logo,
                        category = "Фастфуд",
                        locations = listOf(
                            GeoPoint(55.9840, 37.2000), // Пример: Центр Зеленограда
                            GeoPoint(55.9835, 37.2010) // Пример: Ещё одна точка ВТ
                        )
                    ),
                    RestaurantItem(
                        id = "burger_king",
                        name = "Бургер кинг",
                        lastOrderInfo = "Последний заказ • 2 дня назад",
                        logoResId = R.drawable.bk_logo,
                        category = "Фастфуд",
                        locations = listOf(
                            GeoPoint(55.9750, 37.2050) // Пример: Бургер Кинг
                        )
                    ),
                    RestaurantItem(
                        id = "rostics",
                        name = "Ростикс",
                        lastOrderInfo = "Последний заказ • вчера",
                        logoResId = R.drawable.rost_logo,
                        category = "Фастфуд",
                        locations = listOf(
                            GeoPoint(55.9800, 37.1900) // Пример: Ростикс
                        )
                    )
                    // ДОБАВЬТЕ ВСЕ ДРУГИЕ ВАШИ RestaurantItem С ИХ РЕАЛЬНЫМИ КООРДИНАТАМИ!
                )
            }
            val restaurantsToDisplay = remember(selectedTab, favoriteRestaurantIds.toList()) {
                if (selectedTab == "Избранное") {
                    allRestaurantsInMainClickable.filter { favoriteRestaurantIds.contains(it.id) }
                } else {
                    allRestaurantsInMainClickable
                }
            }

            RestaurantCardsList(
                restaurants = restaurantsToDisplay,
                onCardClick = onCardClick,
                onFavoriteToggle = onFavoriteToggle, // <-- Используем переданный onFavoriteToggle
                isRestaurantFavorite = isRestaurantFavorite
            )
        }
    }

}
@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    fontFamily: FontFamily, // Добавлен fontFamily
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else Color.Transparent, // Светлый фон при выборе
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 20.dp, vertical = 10.dp), // Большие отступы для формы
        contentAlignment = Alignment.Center
    ) {
        Text(
            fontFamily = fontFamily,
            text = text,
            style = TextStyle(fontWeight = FontWeight(800)),
            fontSize = 15.sp,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary, // Цвет текста
        )
    }
}

//NavBar
object AppDestinations {
    val FOOD_ROUTE = "food_route"
    val MAP_ROUTE = "map_route"
    val PROFILE_ROUTE = "profile_route"
    val AUTH_PHONE_NUMBER_ROUTE = "auth_phone_number_route"
    val AUTH_VERIFICATION_CODE_ROUTE = "auth_verification_code_route"
}
data class NavItem(
    val icon: ImageVector,
    val contentDescription: String,
    val route: String // Для навигации
)

val navItems = listOf(
    NavItem(Icons.Filled.RestaurantMenu, "Еда", AppDestinations.FOOD_ROUTE),
    NavItem(Icons.Filled.Map, "Карта", AppDestinations.MAP_ROUTE),
    NavItem(Icons.Filled.Person, "Профиль", AppDestinations.PROFILE_ROUTE)
)
//LoginScreen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberInputScreen(onPhoneNumberEntered: (String) -> Unit) {
    var phoneNumber by remember { mutableStateOf("") }
    val isPhoneNumberValid = phoneNumber.length == 11 // Простая валидация на 10 цифр
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    // Если используете свой шрифт, например:
     val josefont = FontFamily(Font(googleFont = GoogleFont("Jose"), fontProvider = provider))
    // Тогда используйте fontFamily = josefont в Text Composable

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerHigh) // Или другой фон
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.start_logo),
            contentDescription = null,
            modifier = Modifier
                .size(500.dp)
                .offset(y= -40.dp)
        )
        Column(Modifier.offset(y = -120.dp)) {

            Text(
                fontFamily = josefont,
                text = "Введите номер телефона",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { newValue ->
                    if (newValue.length <= 11 && newValue.all { it.isDigit() }) {
                        phoneNumber = newValue
                    }
                },
                label = { Text("Номер телефона") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors( // Используйте OutlinedTextFieldDefaults.colors
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onPhoneNumberEntered(phoneNumber) },
                enabled = isPhoneNumberValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    fontFamily = josefont,
                    text = "Продолжить",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
//validation Screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationCodeInputScreen(
    phoneNumber: String,
    onCodeVerified: () -> Unit
) {
    var verificationCode by remember { mutableStateOf("") }
    val expectedCode = "123456" // <<< ВНИМАНИЕ: Это заглушка! В реальном приложении это будет динамически.
    val isCodeValid = verificationCode.length == 6 && verificationCode == expectedCode
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    // Если используете свой шрифт
     val josefont = FontFamily(Font(googleFont = GoogleFont("Jose"), fontProvider = provider))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.start_logo),
            contentDescription = null,
            modifier = Modifier
                .size(500.dp)
                .offset(y= -40.dp)
        )
        Column(Modifier.offset(y = -120.dp)){
            Text(
                fontFamily = josefont,
                text = "Введите 6-значный код, отправленный на $phoneNumber",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = verificationCode,
                onValueChange = { newValue ->
                    if (newValue.length <= 6 && newValue.all { it.isDigit() }) {
                        verificationCode = newValue
                    }
                },
                label = { Text(fontFamily = josefont, text = "Код подтверждения") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            )

            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onCodeVerified,
                enabled = isCodeValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    fontFamily = josefont,
                    text = "Подтвердить",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
@Composable
fun PathEasing(interpolator: PathInterpolator): Easing = remember(interpolator) {
    Easing { fraction -> interpolator.getInterpolation(fraction) }
}
@Composable
fun CustomBottomNavigationBar(
    navController: NavController
) {
    val customPathInterpolator = remember { PathInterpolator(0.2f, 0.0f, 0.0f, 1.0f) }
    val animatedEasing = PathEasing(customPathInterpolator)

    // Получаем текущий маршрут из NavController
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = Modifier
            .width((currentWindowSize().width / 4).dp) // Этот модификатор выглядит странно для нижнего бара
            // Если вы хотите, чтобы бар был шире, возможно, .fillMaxWidth()
            // или определенная ширина, но (currentWindowSize().width/4)dp сделает его очень узким.
            // Возможно, вы имели в виду (currentWindowSize().width * 0.9f).dp?
            .clip(RoundedCornerShape(50))
            .shadow(elevation = 100.dp, shape = RoundedCornerShape(50))
            .background(
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = RoundedCornerShape(50)
            ),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth() // Должен быть fillMaxWidth для нижнего бара
                .height(64.dp)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navItems.forEach { item ->
                // Определяем, выбран ли текущий элемент по его маршруту
                val isSelected = item.route == currentRoute

                val animatedBackgroundColor by animateColorAsState(
                    targetValue = if (isSelected) MaterialTheme.colorScheme.onTertiaryContainer else Color.Transparent,
                    animationSpec = tween(durationMillis = 800, easing = animatedEasing)
                )

                val animatedIconColor by animateColorAsState(
                    targetValue = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                    animationSpec = tween(durationMillis = 800, easing = animatedEasing)
                )

                val animatedIconSize by animateDpAsState(
                    targetValue = if (isSelected) 28.dp else 24.dp,
                    animationSpec = tween(durationMillis = 800, easing = animatedEasing)
                )

                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(50))
                        .background(animatedBackgroundColor)
                        .clickable {
                            // При клике навигация на соответствующий маршрут
                            if (item.route != currentRoute) {
                                navController.navigate(item.route) {
                                    // Очищаем бэкстек, чтобы при нажатии на элемент
                                    // навигации не создавать кучу одинаковых экранов
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true // Сохраняем состояние предыдущих экранов
                                    }
                                    // Избегаем создания нескольких копий одного и того же маршрута на вершине стека
                                    launchSingleTop = true
                                    // Восстанавливаем состояние при повторном выборе элемента
                                    restoreState = true
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.contentDescription,
                        tint = animatedIconColor,
                        modifier = Modifier.size(animatedIconSize)
                    )
                }
            }
        }
    }
}
data class PointsCardItem(
    val id: String,
    val logoResId: Int,
    val points: Int,
    val name: String,
    val websiteUrl: String
)
@Composable
fun PointsCard(item: PointsCardItem) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .width(120.dp) // Фиксированная ширина карточки, как на изображении
            .height(150.dp)
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.websiteUrl))
                context.startActivity(intent)
            },
        shape = RoundedCornerShape(16.dp), // Закругленные углы для всей карточки
        colors = CardDefaults.cardColors(
            containerColor = Color.White // Белый фон для верхней части, как на скриншоте
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Небольшая тень

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Верхняя часть с логотипом
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp) // Высота верхней части
                    .background(Color.White, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)), // Белый фон с закруглением сверху
                contentAlignment = Alignment.Center
            ) {
                // Логотип
                Image(
                    painter = painterResource(id = item.logoResId),
                    contentDescription = "${item.name} logo", // Описание логотипа
                    modifier = Modifier
                        .size(80.dp) // Размер логотипа
                        .clip(RoundedCornerShape(12.dp)) // Небольшое скругление углов для логотипа, если нужно
                )
            }

            // Нижняя часть с баллами
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp) // Высота нижней части
                    .background(
                        color = MaterialTheme.colorScheme.secondary,//ЭТО ИЗМЕНИТЬ
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${item.points} баллов", // Текст с баллами
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondary // И ЭТОТ ПОЗОР ТОЖЕ
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileClickable(
    onClearData: () -> Unit,
    onLogout: () -> Unit,
    phoneNumber: String
) {
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val pointsItems = listOf(
        PointsCardItem("vkusno_i_tochka_points", R.drawable.vkus_logo, 300, "Вкусно и точка", "https://vkusnoitochka.ru/"), // <-- ДОБАВЛЕН URL
        PointsCardItem("burger_king_points", R.drawable.bk_logo, 271, "Burger King", "https://burgerkingrus.ru"), // <-- ДОБАВЛЕН URL
        PointsCardItem("rostics_points", R.drawable.rost_logo, 200, "Rostic's", "https://rostics.ru/") // <-- ДОБАВЛЕН URL
    )
    //FontFamily
    val josefont = FontFamily(
        Font(
            googleFont = GoogleFont("Jose"),
            fontProvider = provider,
        )
    )
    Box(
        modifier = Modifier
            .size(
                (currentWindowSize().height / 3 + 150).dp
            )
            .offset(y = 140.dp)
            .background(MaterialTheme.colorScheme.surfaceContainerLow),
        contentAlignment = Alignment.TopCenter
    ) {
        Column() {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    fontFamily = josefont,
                    text = "Dimas Lavka",
                    style = TextStyle(fontWeight = FontWeight(800)),// Может, текст поменьше для маленького круга
                    fontSize = 26.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Icon(
                    painter = painterResource(R.drawable.restor),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .width(200.dp)
                        .height(40.dp)
                        .offset(y = 20.dp)
                )
            }
            Spacer(Modifier.height(20.dp))
            Column(Modifier.offset(10.dp)) {

                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Column() {
                        Text(
                            fontFamily = josefont,
                            text = "Телефон",
                            style = TextStyle(fontWeight = FontWeight(800)),// Может, текст поменьше для маленького круга
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            fontFamily = josefont,
                            text = "$phoneNumber",
                            style = TextStyle(fontWeight = FontWeight(800)),// Может, текст поменьше для маленького круга
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
                Spacer(Modifier.height(10.dp))
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Column() {
                        Text(
                            fontFamily = josefont,
                            text = "Почта",
                            style = TextStyle(fontWeight = FontWeight(800)),// Может, текст поменьше для маленького круга
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            fontFamily = josefont,
                            text = "Dimasik@gmail.com",
                            style = TextStyle(fontWeight = FontWeight(800)),// Может, текст поменьше для маленького круга
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.tertiary
                        )

                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                Box(
                    modifier = Modifier
                        .size(height = 5.dp, width = 100.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        ),

                    ) {}
            }
            Spacer(Modifier.height(20.dp))
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .offset(x = 10.dp)
            ) {
                Text(
                    fontFamily = josefont,
                    text = "Баллы в ресторанах",
                    style = TextStyle(fontWeight = FontWeight(800)),// Может, текст поменьше для маленького круга
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally), // Пространство между карточками
                contentPadding = PaddingValues(horizontal = 15.dp) // Отступы по бокам ряда
            ) {
                items(pointsItems)
                { item ->
                    PointsCard(item = item)
                }
            }

            Spacer(Modifier.height(25.dp))
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                Box(
                    modifier = Modifier
                        .size(height = 5.dp, width = 100.dp) // Применяем размер
                        .background( // Применяем фон и форму
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        ),

                    ) {}
            }
            Spacer(Modifier.height(20.dp)) // Пространство перед кнопками
            // Кнопки "Очистить данные" и "Выйти из профиля"
            ProfileButton(
                text = "Очистить данные",
                onClick = onClearData, // Вызываем переданный колбэк
                fontFamily = josefont
            )
            Spacer(Modifier.height(16.dp)) // Пространство между кнопками
            ProfileButton(
                text = "Выйти из профиля",
                onClick = onLogout, // Вызываем переданный колбэк
                fontFamily = josefont
            )
            Spacer(Modifier.height(20.dp)) // Пространство после кнопок

        }

    }

}
@Composable
fun ProfileButton(
    text: String,
    onClick: () -> Unit,
    fontFamily: FontFamily,
    icon: ImageVector? = null, // Опциональная иконка слева
    cardColor: Color = MaterialTheme.colorScheme.primary, // Цвет фона карточки, по умолчанию primary
    textColor: Color = MaterialTheme.colorScheme.onPrimary // Цвет текста, по умолчанию белый
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(CircleShape) // <-- Используем CircleShape здесь для "вытянутого круга"
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center // Центрируем содержимое по горизонтали
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null, // Можно добавить более осмысленное описание
                    tint = textColor, // Цвет иконки как у текста
                    modifier = Modifier.size(24.dp) // Размер иконки
                )
                Spacer(modifier = Modifier.width(16.dp)) // Пространство между иконкой и текстом
            }

            Text(
                text = text,
                fontFamily = fontFamily,
                style = MaterialTheme.typography.titleMedium, // Или другой стиль, подходящий для кнопки
                fontWeight = FontWeight.Bold, // Жирный текст
                fontSize = 18.sp, // Размер текста
                color = textColor
            )
        }
    }
}
val allRestaurantsListFromSource = listOf(
    RestaurantItem(
        id = "vkusno_i_tochka",
        name = "Вкусно и точка",
        lastOrderInfo = "Последний заказ • неделю назад",
        logoResId = R.drawable.vkus_logo, // Замените на реальные ресурсы
        category = "Биг Спешал со скидкой 20%",
        locations = listOf(
            GeoPoint(55.982320, 37.175118), // Центральный Зеленоград
            GeoPoint(55.9835, 37.2010),
            GeoPoint(56.014343, 37.205150),
            GeoPoint(55.964959, 37.186201)
        )
    ),
    RestaurantItem(
        id = "burger_king",
        name = "Бургер кинг",
        lastOrderInfo = "Последний заказ • 2 дня назад",
        logoResId = R.drawable.bk_logo,
        category = "Легендарный Воппер со скидкой 50%",
        locations = listOf(
            GeoPoint(55.983346, 37.174764),
            GeoPoint(56.008400, 37.199129)
        )
    ),
    RestaurantItem(
        id = "rostics",
        name = "Ростикс",
        lastOrderInfo = "Последний заказ • вчера",
        logoResId = R.drawable.rost_logo,
        category = "ТОЛЬКО СЕГОДНЯ 2 ПРОЦИИ ТЕРИЯКИ БАЙТСЕВ ПО ЦЕНЕ 1 (Кринжанул)",
        locations = listOf(
            GeoPoint(56.008842, 37.199077),
            GeoPoint(56.002215, 37.210237),
            GeoPoint(56.000459, 37.258023),
            GeoPoint(55.982704, 37.175974),
            GeoPoint(55.980403, 37.170369),
            GeoPoint(55.983218, 37.142532)

            // Восточный Зеленоград
        )
    )
)

fun Drawable.toBitmap(): Bitmap {
    if (this is BitmapDrawable) {
        return bitmap
    }
    // Для векторных или других типов Drawable рисуем их на Bitmap
    val width = if (intrinsicWidth > 0) intrinsicWidth else 1
    val height = if (intrinsicHeight > 0) intrinsicHeight else 1

    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)
    return bitmap
}

// Вспомогательная функция для масштабирования Bitmap
fun Bitmap.scaleTo(newWidth: Int, newHeight: Int): Bitmap {
    return Bitmap.createScaledBitmap(this, newWidth, newHeight, true) // true для сглаживания
}
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    favoriteRestaurantIds: SnapshotStateList<String>
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val desiredIconSizeDp = 20.dp
    val density = LocalDensity.current // <-- Получаем плотность экрана
// <-- Измените это значение (например, 32.dp, 64.dp)
    // Конвертируем dp в пиксели
    val desiredIconSizePx = with(density) { desiredIconSizeDp.toPx().toInt() }
    val osmdroidConfiguration = remember { Configuration.getInstance() }
    LaunchedEffect(Unit) {
        osmdroidConfiguration.load(
            context,
            context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE)
        )
        osmdroidConfiguration.userAgentValue = context.packageName
    }

    val locationPermissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    LaunchedEffect(locationPermissionsState.allPermissionsGranted) {
        Log.d("MapScreen", "Permissions granted: ${locationPermissionsState.allPermissionsGranted}")
        Log.d("MapScreen", "Permissions status: ${locationPermissionsState.permissions.map { "${it.permission}: ${it.status}" }}")
    }

    LaunchedEffect(Unit) {
        if (!locationPermissionsState.allPermissionsGranted) {
            locationPermissionsState.launchMultiplePermissionRequest()
        }
    }

    var mapView: MapView? by remember { mutableStateOf(null) }
    var myLocationOverlay: MyLocationNewOverlay? by remember { mutableStateOf(null) } // <-- Ссылка на оверлей местоположения

    var showAllRestaurantsOnMap by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
        if (!locationPermissionsState.allPermissionsGranted) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Для отображения карты и вашего местоположения необходимо предоставить разрешение на доступ к местоположению.",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(onClick = { locationPermissionsState.launchMultiplePermissionRequest() }) {
                    Text("Предоставить разрешение")
                }
            }
        } else {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { ctx ->
                    MapView(ctx).apply {
                        setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK)
                        controller.setCenter(GeoPoint(55.9840, 37.2000)) // Начальный центр карты (Зеленоград)
                        controller.setZoom(13.0)
                        setMultiTouchControls(true)

                        val locationProvider = GpsMyLocationProvider(ctx)
                        val myLocationOverlay = MyLocationNewOverlay(locationProvider, this)
                        myLocationOverlay.enableMyLocation()
                        overlays.add(myLocationOverlay)


                        invalidate()
                        mapView = this
                    }
                },
                update = { currentMapView ->
                    currentMapView?.let { map ->
                        val markersToRemove = map.overlays.filterIsInstance<Marker>()
                            .filter { it.id?.startsWith("restaurant_") == true }
                        map.overlays.removeAll(markersToRemove.toSet())

                        val restaurantsToDisplayOnMap = if (showAllRestaurantsOnMap) {
                            allRestaurantsListFromSource
                        } else {
                            allRestaurantsListFromSource.filter { restaurantItem ->
                                favoriteRestaurantIds.contains(restaurantItem.id)
                            }
                        }

                        restaurantsToDisplayOnMap.forEach { restaurantItem ->
                            restaurantItem.locations.forEachIndexed { index, location ->
                                val marker = Marker(map)
                                marker.position = location
                                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                                marker.title = restaurantItem.name
                                marker.snippet = restaurantItem.category
                                marker.id = "restaurant_${restaurantItem.id}_$index"
                                val originalIconDrawable = ContextCompat.getDrawable(context, restaurantItem.logoResId)

                                originalIconDrawable?.let { drawable ->
                                    val scaledBitmap = drawable.toBitmap().scaleTo(desiredIconSizePx, desiredIconSizePx)
                                    marker.icon = BitmapDrawable(context.resources, scaledBitmap)
                                }

                                map.overlays.add(marker)
                            }
                        }
                        map.invalidate()
                    }
                    mapView = currentMapView
                }
            )
        }


        if (locationPermissionsState.allPermissionsGranted) {
            Button(
                onClick = { showAllRestaurantsOnMap = !showAllRestaurantsOnMap },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(80.dp))
            ) {
                Text(
                    text = if (showAllRestaurantsOnMap) "Показать избранное" else "Показать все",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

        } else {
            Log.d("MapScreen", "Buttons not displayed: Permissions not granted.")
        }
    }

    DisposableEffect(lifecycleOwner, mapView, myLocationOverlay) { // <-- Добавили myLocationOverlay в зависимости
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    Log.d("MapScreen", "MapView onResume")
                    mapView?.onResume()
                    myLocationOverlay?.enableMyLocation() // <-- Включить отслеживание местоположения
                }
                Lifecycle.Event.ON_PAUSE -> {
                    Log.d("MapScreen", "MapView onPause")
                    mapView?.onPause()
                    myLocationOverlay?.disableMyLocation() // <-- Отключить отслеживание местоположения для экономии батареи
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            myLocationOverlay?.disableMyLocation() // Отключить при диспозе
            mapView?.overlays?.remove(myLocationOverlay) // Удалить оверлей при диспозе
        }
    }
}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ProfileShapeCurrnet(){
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    //FontFamily
    val josefont = FontFamily(
        Font(
            googleFont = GoogleFont("Jose"),
            fontProvider = provider,
            //weight = FontWeight.
        )
    )
    val shape = Companion.Cookie12Sided.normalized().toShape()
    val avaShape = Companion.VerySunny.normalized().toShape()
    Box(
        modifier = Modifier
            .requiredWidth((currentWindowSize().width).dp)
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        contentAlignment = Alignment.TopCenter
    ) {
        Icon(
            painter = painterResource(R.drawable.arrow),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(180.dp)
                .offset(x = -120.dp, y = -7.dp)
                .rotate(-35f)
        )
        Surface(
            shape = shape,
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            shadowElevation = 20.dp,
            modifier = Modifier
                .size(
                    (currentWindowSize().width * 0.5f).dp,
                    (currentWindowSize().height * 0.25f).dp
                )
        ) {
            Box(
                modifier = Modifier.
                fillMaxSize()
                    .offset(y = 50.dp),
                contentAlignment = Alignment.TopCenter// Эта обертка займет весь Surface

            ) {
                Icon(
                    painter = painterResource(R.drawable.ava),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(40.dp)
                        .offset(x = -45.dp, y = -15.dp)

                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(avaShape)
                        //.offset(y = 40.dp)
                        .size(80.dp) // Диаметр 30dp
                        .background(
                            color = MaterialTheme.colorScheme.primaryFixed,
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ava_pic), // Замените your_image_name на имя вашего файла в res/drawable
                        contentDescription = "ava", // Описание для доступности
                        contentScale = ContentScale.Crop, // Как изображение будет масштабироваться (Crop, Fit, FillBounds и т.д.)
                        modifier = Modifier
                            .fillMaxSize() // Изображение заполняет весь Box
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ProfileScreen(
    onClearFavoriteRestaurants: () -> Unit,
    modifier: Modifier = Modifier,
    userPhoneNumber: String,
    onLogout: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
            Column(
                modifier = Modifier

                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BackgroundCompose()
                Box(){
                    ProfileShapeCurrnet()
                    ProfileClickable(onClearData = onClearFavoriteRestaurants,
                        onLogout = onLogout,
                        phoneNumber = userPhoneNumber)

                }

            }
        }

}
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ShapeCurrnet(){
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    //FontFamily
    val josefont = FontFamily(
        Font(
            googleFont = GoogleFont("Jose"),
            fontProvider = provider,
            //weight = FontWeight.
        )
    )
    val shape = Companion.Cookie12Sided.normalized().toShape()
    Box(
        modifier = Modifier
            .requiredWidth((currentWindowSize().width).dp)
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        contentAlignment = Alignment.TopCenter
    ) {
        Icon(
            painter = painterResource(R.drawable.arrow),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(180.dp)
                .offset(x = -120.dp, y = -7.dp)
                .rotate(-35f)
        )
    Surface(
        shape = shape,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        shadowElevation = 20.dp,
        modifier = Modifier
            .size(
                (currentWindowSize().width * 0.5f).dp,
                (currentWindowSize().height * 0.25f).dp
            )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter// Эта обертка займет весь Surface

        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier

                    .offset(y = 70.dp)

                    .size(width = 200.dp, height = 30.dp) // Диаметр 30dp

                    .background(
                        color = MaterialTheme.colorScheme.primaryFixed,
                        shape = CircleShape
                    )
            ) {

                Text(
                    fontFamily = josefont,
                    text = "Добрый день, Dimas",
                    style = TextStyle(fontWeight = FontWeight(800)),// Может, текст поменьше для маленького круга
                    fontSize = 15.sp,
                    // Настройте
                    color = MaterialTheme.colorScheme.onPrimaryFixed
                )
            }
        }
    }
    }
}
data class PromotionItem(
    val id: String,
    val imageUrl: Int, // Идентификатор ресурса drawable для картинки акции
    val title: String,
    val description: String,
    val websiteUrl: String // Ссылка для перехода
)

// Composable для отображения отдельной карточки акции
@Composable
fun PromotionCard(promotion: PromotionItem) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp) // Отступ между карточками акций
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(promotion.websiteUrl))
                context.startActivity(intent)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant // Цвет фона карточки акции
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp), // Небольшая тень
        shape = RoundedCornerShape(8.dp) // Скругленные углы для карточки акции
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp), // Внутренний отступ внутри карточки
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Изображение акции (слева, квадратное)
            Image(
                painter = painterResource(id = promotion.imageUrl),
                contentDescription = promotion.title,
                modifier = Modifier
                    .size(64.dp) // Размер квадратного изображения
                    .clip(RoundedCornerShape(8.dp)) // Скругленные углы для изображения
                    .background(Color.LightGray), // Заглушка фона, если изображение не загрузилось
                contentScale = ContentScale.Crop // Обрезка изображения для заполнения
            )

            Spacer(modifier = Modifier.width(12.dp)) // Отступ между изображением и текстом

            // Описание акции (справа)
            Column(modifier = Modifier.weight(1f)) { // Занимает оставшееся пространство
                Text(
                    text = promotion.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = promotion.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }
        }
    }
}

// Composable для всплывающего окна деталей ресторана
@Composable
fun RestaurantDetailPopup(
    restaurant: RestaurantItem,
    promotions: List<PromotionItem> // Теперь принимает список акций
) {
    // Этот Box - это ваш основной контейнер всплывающего окна
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f) // Ширина 80% от родителя
            .wrapContentHeight() // Высота по содержимому
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(30.dp)
            )
            .padding(24.dp)
            .clickable(enabled = false) { /* no-op */ } // Предотвращаем закрытие при нажатии внутри
        ,
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "${restaurant.name}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${restaurant.lastOrderInfo}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.height(16.dp)) // Отступ перед акциями

            // --- Отображаем карточки акций, переданные в качестве параметра ---
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (promotions.isNotEmpty()) {
                    promotions.forEach { promotion ->
                        PromotionCard(promotion = promotion) // Используем новый Composable
                    }
                } else {
                    // Опционально: показать сообщение, если акций нет
                    Text(
                        text = "Акций для этого ресторана пока нет.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun mainScreen() {
    val navController = rememberNavController()
    var userPhoneNumber by rememberSaveable { mutableStateOf("") }

    var selectedRestaurantForPopup by remember { mutableStateOf<RestaurantItem?>(null) }
    var selectedCarouselItemForPopup by remember { mutableStateOf<CarouselItem?>(null) }
    val favoriteRestaurantIds = remember { mutableStateListOf<String>() }


    var isAuthenticated by rememberSaveable { mutableStateOf(false) }

    val restaurantPromotions: Map<String, List<PromotionItem>> = remember {
        mapOf(
            "vkusno_i_tochka" to listOf(
                PromotionItem(
                    id = "v_promo_1",
                    imageUrl = R.drawable.big_speshial, // Замените на ваши ресурсы
                    title = "20% на Биг Спешал",
                    description = "Успей воспользоваться!",
                    websiteUrl = "https://vkusnoitochka.ru/"
                ),
                PromotionItem(
                    id = "v_promo_2",
                    imageUrl = R.drawable.big_hit,
                    title = "Биг Хит со скидкой",
                    description = "Биг Хит за 150₽",
                    websiteUrl = "https://vkusnoitochka.ru/"
                ),
                PromotionItem(
                    id = "v_promo_3",
                    imageUrl = R.drawable.combo,
                    title = "Выгодное Комбо",
                    description = "Комбо за 160₽",
                    websiteUrl = "https://vkusnoitochka.ru/"
                )
            ),
            "burger_king" to listOf(
                PromotionItem(
                    id = "bk_promo_1",
                    imageUrl = R.drawable.burger_whopper, // Замените на ваши ресурсы
                    title = "Воппер за полцены",
                    description = "Каждый понедельник - легендарный Воппер со скидкой 50%!",
                    websiteUrl = "https://burgerkingrus.ru/"
                ),
                PromotionItem(
                    id = "bk_promo_2",
                    imageUrl = R.drawable.big_king,
                    title = "Биг Кинг",
                    description = "Биг Кинг за 300₽",
                    websiteUrl = "https://burgerkingrus.ru"
                ),
                PromotionItem(
                    id = "bk_promo_3",
                    imageUrl = R.drawable.cheesburger,
                    title = "Чизбургер",
                    description = "Чизбургер за 65₽",
                    websiteUrl = "https://burgerkingrus.ru"
                )
            ),
            "rostics" to listOf(
                PromotionItem(
                    id = "r_promo_1",
                    imageUrl = R.drawable.img_20250317_073535, // Замените на ваши ресурсы
                    title = "5050",
                    description = "2 Порции Терияки Байтсов по цене 1",
                    websiteUrl = "https://rostics.ru/"
                ),
                PromotionItem(
                    id = "r_promo_2",
                    imageUrl = R.drawable.kawa,
                    title = "Кофе Бесплатно",
                    description = "5-я кружка кофе в подарок",
                    websiteUrl = "https://rostics.ru/"
                ),
                PromotionItem(
                    id = "r_promo_3",
                    imageUrl = R.drawable.tortik,
                    title = "20% на тортик",
                    description = "с МТС Банк тортки со скидкой 20%",
                    websiteUrl = "https://rostics.ru/"
                )
            )
            // Добавьте другие рестораны и их акции здесь
        )
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
        NavHost(
            navController = navController,
            startDestination = if (isAuthenticated) AppDestinations.FOOD_ROUTE else AppDestinations.AUTH_PHONE_NUMBER_ROUTE
        ) {
            composable(AppDestinations.AUTH_PHONE_NUMBER_ROUTE) {
                PhoneNumberInputScreen(
                    onPhoneNumberEntered = { phoneNumber ->
                        navController.navigate("${AppDestinations.AUTH_VERIFICATION_CODE_ROUTE}/$phoneNumber")
                    }
                )
            }
            composable(
                "${AppDestinations.AUTH_VERIFICATION_CODE_ROUTE}/{phoneNumber}",
                arguments = listOf(navArgument("phoneNumber") { type = NavType.StringType })
            ) { backStackEntry ->
                val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
                VerificationCodeInputScreen(
                    phoneNumber = phoneNumber,
                    onCodeVerified = {
                        userPhoneNumber = phoneNumber
                        isAuthenticated = true
                        navController.navigate(AppDestinations.FOOD_ROUTE) {
                            popUpTo(AppDestinations.AUTH_PHONE_NUMBER_ROUTE) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(AppDestinations.FOOD_ROUTE) {
                Scaffold(
                    containerColor = Color.Transparent,
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        BackgroundCompose()
                        Box {
                            ShapeCurrnet()
                            MainClickable(
                                onCardClick = { item -> selectedRestaurantForPopup = item },
                                onCarouselItemClick = { item -> selectedCarouselItemForPopup = item },
                                favoriteRestaurantIds = favoriteRestaurantIds,
                                onFavoriteToggle = { restaurant ->
                                    if (favoriteRestaurantIds.contains(restaurant.id)) {
                                        favoriteRestaurantIds.remove(restaurant.id)
                                    } else {
                                        favoriteRestaurantIds.add(restaurant.id)
                                    }
                                },
                                onShowMap = {
                                    navController.navigate(AppDestinations.MAP_ROUTE)
                                }
                            )
                        }
                    }
                }
            }

            composable(AppDestinations.MAP_ROUTE) {
                Scaffold(
                ) { innerPadding ->
                    MapScreen(
                        modifier = Modifier.padding(innerPadding),
                        favoriteRestaurantIds = favoriteRestaurantIds// <-- УСТАНАВЛИВАЕМ ЗНАЧЕНИЕ ДЛЯ ПОПАПА

                    )
                }
            }

            composable(AppDestinations.PROFILE_ROUTE) {
                Scaffold(
                    containerColor = Color.Transparent,
                ) { innerPadding ->
                    ProfileScreen(
                        onClearFavoriteRestaurants = { favoriteRestaurantIds.clear() },
                        modifier = Modifier.padding(innerPadding),
                        userPhoneNumber = userPhoneNumber,
                        // --- ВОТ ТВОЯ ЛОГИКА ВЫХОДА ИЗ ПРОФИЛЯ ---
                        onLogout = {
                            isAuthenticated = false // <-- СБРОСИТЬ СОСТОЯНИЕ АУТЕНТИФИКАЦИИ
                            navController.navigate(AppDestinations.AUTH_PHONE_NUMBER_ROUTE) {
                                // <-- НАВИГАЦИЯ НА ЭКРАН АВТОРИЗАЦИИ
                                // Очистить весь Back Stack, чтобы пользователь не мог вернуться назад
                                popUpTo(navController.graph.id) {
                                    inclusive = true // Включить сам стартовый экран в очистку
                                }
                            }
                        }
                    )
                }
            }
        }

        if (selectedCarouselItemForPopup != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable {
                        selectedCarouselItemForPopup = null
                    },
                contentAlignment = Alignment.Center
            ) {
                AnimatedVisibility(
                    visible = selectedCarouselItemForPopup != null,
                    enter = fadeIn() + scaleIn(initialScale = 0.8f),
                    exit = fadeOut() + scaleOut(targetScale = 0.8f)
                ) {
                    selectedCarouselItemForPopup?.let { item ->
                        CarouselPopup(
                            item = item,
                            onDismiss = { selectedCarouselItemForPopup = null }
                        )
                    }
                }
            }
        }

        if (selectedRestaurantForPopup != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { selectedRestaurantForPopup = null }, // Закрыть попап по клику вне
                contentAlignment = Alignment.Center
            ) {
                AnimatedVisibility(
                    visible = selectedRestaurantForPopup != null,
                    enter = fadeIn() + scaleIn(initialScale = 0.3f),
                    exit = fadeOut() + scaleOut(targetScale = 0.3f)
                ) {
                    selectedRestaurantForPopup?.let { restaurant ->
                        RestaurantDetailPopup(
                            restaurant = restaurant,
                            promotions = restaurantPromotions[restaurant.id] ?: emptyList()
                        )
                    }
                }
            }
        }

        // Условное отображение CustomBottomNavigationBar
        if (isAuthenticated && (navController.currentDestination?.route == AppDestinations.FOOD_ROUTE ||
                    navController.currentDestination?.route == AppDestinations.MAP_ROUTE ||
                    navController.currentDestination?.route == AppDestinations.PROFILE_ROUTE)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 30.dp)
                    .align(Alignment.BottomCenter),
                contentAlignment = Alignment.Center
            ) {
                CustomBottomNavigationBar(navController = navController)
            }
        }
    }
}

@Composable
fun actions(
    rest_name: String
){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Купоны ${rest_name}",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        if(rest_name == "Вкусно и точка"){
            Column()
            {
                Box(Modifier
                    .fillMaxWidth()
                ) {

                }

            }
        }
        Spacer(modifier = Modifier.height(8.dp))



    }
}

@Composable
fun MainScr(){
    EatTheme {
    mainScreen()
    }
}
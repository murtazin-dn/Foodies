# Foodies

## Описание

Foodies - это мобильное приложение для заказа еды, разработанное с использованием Jetpack Compose и архитектуры MVVM. Приложение позволяет пользователям просматривать каталог блюд, добавлять их в корзину и оформлять заказы. Приложение поддерживает фильтрацию и поиск блюд.

## Структура проекта

Проект разделен на несколько модулей, каждый из которых имеет свою ответственность:

- **`app`**: основной модуль приложения
  - [`MainActivity`](app/src/main/java/com/example/foodies/MainActivity.kt) - главная активность приложения.
  - [`FoodiesApplication`](app/src/main/java/com/example/foodies/FoodiesApplication.kt) - класс `Application`, отвечающий за инициализацию зависимостей.
  - [`FoodiesNavHost`](app/src/main/java/com/example/foodies/navigation/FoodiesNavHost.kt) - навигационный хост приложения.

- **Feature модули**:
  - **`feature/catalog`**: содержит экран каталога.
  - **`feature/productDetail`**: содержит экран деталей продукта.
  - **`feature/feature_cart`**: содержит экран корзины.

- **Core модули**:
  - **`core/network`**: содержит сетевые компоненты и API.
    - [`FoodiesRetrofit`](core/network/src/main/java/com/example/network/retrofit/FoodiesRetrofit.kt) - реализация сетевого слоя с использованием Retrofit.
  - **`core/designsystem`**: содержит общие стили и UI компоненты.
  - **`core/data`**: содержит логику работы с данными.
  - **`core/domain`**: содержит бизнес логику приложения.

## Технологии

Проект использует следующие технологии и библиотеки:

- **Языки и Фреймворки**:
  - Kotlin
  - Jetpack Compose

- **Библиотеки**:
  - Dagger - для внедрения зависимостей
  - Retrofit - для сетевых запросов
  - kotlinx.serialization - для сериализации JSON
  - AndroidX:
    - Navigation Component - для навигации
    - Lifecycle - для управления жизненным циклом компонентов
    - SplashScreen - для реализации экрана загрузки

- **Тестирование**:
  - JUnit - для модульного тестирования
  - Espresso - для UI тестирования


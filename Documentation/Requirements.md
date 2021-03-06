## Требования к проекту

### Содержание
1. [Введение](#1)<br>
2. [Требования пользователя](#2)<br>
 2.1 [Программные интерфейсы](#2.1)<br>
 2.2 [Интерфейс пользователей](#2.2)<br>
 2.3 [Характеристики пользователей](#2.3)<br>
3. [Системные требования](#3)<br>
 3.1 [Функциональные требования](#3.1)<br>
 3.2 [Нефункциональные требования](#3.2)<br>
   3.2.1 [Атрибуты качества](#3.2.1)<br>
   3.2.2 [Ограничения](#3.2.2)<br>
4. [Аналоги](#4)<br>


### 1. Введение<a name="1"></a>
Погода является неотъемлемым спутником каждого обитателя Земли, оказывающим на него непосредственное влияние. Приложение WeatherApp предоставляет своему пользователю информацию о климатических условиях в интересующей его местности в удобной для него форме.
### 2. Требования пользователей<a name="2"></a>

#### 2.1 Программные интерфейсы<a name="2.1"></a>
Приложение написано на Java, функционирует на базе платформы Android. Для получения информации о погоде используется бесплатная версия OpenWeatherMap API.
#### 2.2 Интерфейс пользователя<a name="2.2"></a>

- Главная страница

![Главная страница](https://github.com/eBlow/WeatherApp/blob/master/Documentation/Mockups/Main.png)

- Меню настроек

![Меню настроек](https://github.com/eBlow/WeatherApp/blob/master/Documentation/Mockups/Settings.png)

- Настройка формата отображения даты

![Формат даты](https://github.com/eBlow/WeatherApp/blob/master/Documentation/Mockups/Date%20format.png)

#### 2.3 Характеристики пользователей<a name="2.3"></a>
Целевая аудитория проекта:
- Люди с особым эстетическим восприятием.
### 3. Системные требования<a name="3"></a>
#### 3.1 Функциональные требования<a name="3.1"></a>
Пользователю предоставлены следующие возможности:

| Функция | Требования | 
|:---|:---|
| Обновление информации о погоде | При запуске приложения отправляется запрос к соответствующей базе данных. |
| Отображение информации о погоде | Информация о погоде, полученная от базы данных, отображается на главном экране. |
| Настройка отображения информации | Пользователь имеет возможность настройки единиц измерения, в которых отображается информация о погоде. |

#### 3.2 Нефункциональные требования<a name="3.2"></a>
##### 3.2.1 Атрибуты качества<a name="3.2.1"></a>
Важными атрибутами качества проекта являются плавность работы интерфейса и его лаконичность.
##### 3.2.2 Ограничения<a name="3.2.2"></a>
1. Устройство на Android 6.0 или выше.
2. Для получения актуальной информации о погоде необходимо наличие интернет-соединения.
#### 4.  Аналоги <a name="4"></a>
Существует множество аналогов разрабатываемого приложения, превосходящих его по функциональности ввиду использования информации из более "продвинутых" баз данных. Основное преимущество приложения "WeatherApp" субъективно – лаконичный интерфейс.

-   [Яндекс.Погода](https://play.google.com/store/apps/details?id=ru.yandex.weatherplugin&hl=ru)
    -   Приложение для ознакомления с текущими и прогнозируемыми погодными условиями от Яндекс. Обладает богатым функционалом, в том числе предоставляет информацию на основе текущего местоположения устройства и отображает осадки на географической карте.
-   [Погода в кармане](https://play.google.com/store/apps/details?id=by.gis.gidrometweather&hl=ru)
    -   Официальное приложение от Белгидромета.


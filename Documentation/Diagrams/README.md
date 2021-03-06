# UML Диаграммы
1. [Диаграмма прецедентов](#1)<br>
1.1 [Актёры](#1.1)<br>
1.2 [Варианты использования](#1.2)<br>
1.2.1 [Просмотр информации о погоде](#1.2.1)<br>
1.2.2 [Обновление информации](#1.2.2)<br>
1.2.3 [Изменение параметров](#1.2.3)<br>
2. [Диаграммы активности](#2)<br>
2.1 [Просмотр информации о погоде и обновление информации](#2.1)<br>
2.2 [Изменение параметров](#2.2)<br>
3. [Диаграмма последовательности](#3)<br>
4. [Диаграмма состояни](#4)<br>
5. [Диаграмма классов](#5)<br>
6. [Диаграмма компонентов и диаграмма развёртывания](#6)<br>

### 1. Диаграмма прецедентов<a name="1"></a>

![Use Case](https://github.com/eBlow/WeatherApp/blob/master/Documentation/Diagrams/Use%20Case%20Diagram.jpg)

#### 1.1 Актёры<a name="1.1"></a>

Актёр | Описание
--- | ---
Пользователь|Человек, использующий приложение для просмотра информации о погоде.

#### 1.2 Варианты использования<a name="1.2"></a>

##### 1.2.1 Просмотр информации о погоде<a name="1.2.1"></a>
**Описание.** Вариант использования "Просмотр информации о погоде" позволяет пользователю ознакомиться с текущей погодой и прогнозом погоды на ближайшие 5 дней, включая сегодняшний.
Поток событий:
1. Пользователь запускает приложение.
2. Приложение загружает информацию о погоде с базы данных. В случае невозможности загрузки информации выполняется альтернативный поток А1.
3. Приложение обрабатывает информацию о текущей погоде.
4. Приложение загружает прогноз погоды с базы данных. В случае невозможности загрузки информации выполняется альтернативный поток А1.
5. Приложение обрабатывает полученный прогноз.
6. Приложение демонстрирует пользователю результат обработки полученной информации.
7. Конец.

Альтернативный поток А1:
1. Приложение информирует пользователя о невозможности загрузки данных.
2. Конец

##### 1.2.2 Обновление информации<a name="1.2.2"></a>
**Описание.** Вариант использования "Обновление информации" позволяет пользователю актуализировать отображаемые приложением данные.
Поток событий:
1. Пользователь выбирает опцию "Обновить информацию".
2. Приложение загружает информацию о погоде с базы данных. В случае невозможности загрузки информации выполняется альтернативный поток А1.
3. Приложение обрабатывает информацию о текущей погоде.
4. Приложение загружает прогноз погоды с базы данных. В случае невозможности загрузки информации выполняется альтернативный поток А1.
5. Приложение обрабатывает полученный прогноз.
6. Приложение демонстрирует пользователю результат обработки полученной информации.
7. Конец.

##### 1.2.3 Изменение параметров<a name="1.2.3"></a>
**Описание.** Вариант использования "Изменение параметров" позволяет пользователю выбрать местность, для которой отображается информация, и персонализировать отображение информации.
Поток событий:
1. Пользователь нажимает кнопку "Настройки".
2. Открывается окно настроек.
3. Пользователь устанавливает имеющиеся настройки в соответствии со своими пожеланиями и рпдоставляемыми приложением возможностями.
4. Пользователь закрывает окно настроек. В случае смены указанного местоположения выполняется альтернативный поток А2.
5. Отображение информации приводится в соответствие с установленными параметрами.
5. Конец.

Альтернативный поток А2:
1. Приложение загружает информацию о погоде с базы данных. В случае невозможности загрузки информации выполняется альтернативный поток А1.
2. Приложение обрабатывает информацию о текущей погоде.
3. Приложение загружает прогноз погоды с базы данных. В случае невозможности загрузки информации выполняется альтернативный поток А1.
4. Приложение обрабатывает полученный прогноз.
5. Приложение демонстрирует пользователю результат обработки полученной информации в соответствии с установленными пользователем параметрами.
6. Конец.

### 2. Диаграммы активности<a name="2"></a>
##### 2.1 Просмотр информации о погоде и обновление информации<a name="2.1"></a> 
При запуске приложения или при обновлении информации происходит обращение к базе данных OpenWeatherMap, полученные данные обрабатываются и результат обработки демонстрируется пользователю. Отличие алгоритма варианта использования "Просмотр информации о погоде" от варианта использования "Обновление информации" заключается в точке входа – запуск приложения и выбор опции "Обновить информацию" соответственно.

![Load data activity](https://github.com/eBlow/WeatherApp/blob/master/Documentation/Diagrams/LoadDataActivity.jpg)

##### 2.2 Изменение параметров<a name="2.2"></a> 
Пользователь изменяет отображаемую информацию.

![Change settings activity](https://github.com/eBlow/WeatherApp/blob/master/Documentation/Diagrams/ChangeSettingsActivity.jpg)

### 3. Диаграмма последовательности<a name="3"></a>
Диаграмма последовательности для основного варианта использования представлена ниже.

![Sequence Diagram](https://github.com/eBlow/WeatherApp/blob/master/Documentation/Diagrams/GetForecastSequence.jpg)

### 4. Диаграмма состояний<a name="4"></a>
Ниже приведена общая диаграмма состояний приложения. 

![State Machine Diagram](https://github.com/eBlow/WeatherApp/blob/master/Documentation/Diagrams/State%20Machine%20Diagram.jpg)

### 5. Диаграмма классов<a name="5"></a>

![Class Diagram](https://github.com/eBlow/WeatherApp/blob/master/Documentation/Diagrams/Class.jpg)

### 6. Диаграмма компонентов и диаграмма развёртывания<a name="6"></a>
Ниже представлена диаграмма компонентов, совмещённая с диаграммой развёртывания.

![Deployment Diagram](https://github.com/eBlow/WeatherApp/blob/master/Documentation/Diagrams/Deployment%20Diagram.jpg)
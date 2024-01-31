# assessmentHealth
___
## Диаграммы
### Диаграмма классов
![Диаграмма классов](https://github.com/SirKokos/assessmentHealth/blob/main/doc/2.jpg)


### Диаграмма использования
![Диаграмма классов](https://github.com/SirKokos/assessmentHealth/blob/main/doc/1.jpg)

### Таблица детализации
<table>
<tr><th>Вариант использования</th><th>Детализация</th></tr> <!--ряд с ячейками заголовков-->

<tr>
    <td>visitDoctor(Patient patient)</td>
    <td>
       Основной вариант использования, при котором происходит оценка здоровья пациента, а также если необходимо вызывается метод который дает рекомендации.
        <br><hr>
       <u> <i>Входные параметры visitDoctor():</i></u><br>
        Объект Patient.
        <br><hr>
        <u><i> Включающий метод: </i></u><br>
        assesmentHealth(int ID) 
        <p> Вызывается для оценки здоровья пациента в процентной шкале (0% – 100%). Обрабатывает поля с его симптомами, личной информацией(пол, возраст), анализы.</p>
        <br><hr>
        <u><i>Расширяющий метод:</i></u><br>
        heallingRecom(int ID)
        <p>Метод который вызывается если оценка здоровья пациента ниже 85%. То происходит рекомендации от системы. По которым смотрится в каких анализах или симптомах пациент нуждается. Возвращает список.</p>
</td>

<tr>
    <td>arivialDoctor(Patient patient)</td>
    <td>
       Основной вариант использования, при котором происходит формирование даты приезда врача на дом.
        <br><hr>
         <u> <i>Входные параметры arivialDoctor():</i></u><br>
        Объект Patient.
        <br><hr>
         <u> <i>Включающий метод:</i></u><br>
        determinationUrgency(int ID)
        <p>
            Метод который оценивает срочность вызова. Так же берет данные с полей:  Обрабатывает поля с его симптомами, личной информацией(пол, возраст), 
            Также берет адрес пациента. На основе этого происходит оценка срочности 0 – 100%.
        </p>
        preparation(int ID)
        <p>
            метод который дает список самых необходимых препаратов для выезда.
        </p>
        <br><hr>
        <u><i>Расширяющий метод:</i></u><br>
        heallingRecom(int ID)
        <p>Метод который вызывается если оценка здоровья пациента ниже 85%. То происходит рекомендации от системы. По которым смотрится в каких анализах или симптомах пациент нуждается. Возвращает список.</p>
</td>
<tr>
    <td>calculatePrice(Patient patient)</td>
    <td>
        Основной вариант использования формирование цены которую должен оплатить клиент.
        <br><hr>
         <u> <i>Входные параметры calculatePrice():</i></u><br>
        Объект Patient.
        <br><hr>
         <u><i>Включающий метод:</i></u><br>
        calcTotalDrug(int ID)
        <p>Метод для подсчета медикаментов и анализов которые были затрачены на пациента, кол-во визитов.</p>
        calcExpDoctor(int ID)
        <p>Просчет опыта врача на основе его стажа, научных работ,  объем работ, стаж в самой компании. </p>
         <u> <i>Расширяющий метод:</i></u><br>
        TotalReport(int ID)
        <p>Метод который должен формировать URL до расположения pdf файла в котором будет собрана вся информация об осуществленных работах над пациентом без пока доли врача.</p>
</td>

</tr> 
</table>
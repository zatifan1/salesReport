# salesReport

Ссылка на документацию https://app.apiary.io/salesreportapi1

1) Для запуска сервера добавьте salesReport/out/artifacts/Java_Web/Java_Web.war в Tomcat. Application context = "\\" , иначе невозможно тестировать

2) Так же для работы с базой данных создайте пользователя 'sweater_user' с паролем 'root' и базу данных 'sales_db'

3) В базе данных 'sales_db' запустите скрипт db.sql , он создаст необходимые таблицы, а так же добавить заранее подготовленные данные

Для теста сервера из приложения добавьте salesReport/out/artifacts/Java_jar/Java.jar

Тестировать можно вызвав методы(возвращают JSON строку):

-Application.newProductPOST(String name) - создает продукт

-Application.newProductGET() - возвращает все продукты

-Application.purchasePOST(String name, int amount, int price, String date) - создает покупку

-Application.purchaseGET() - возвращает покупки

-Application.demandPOST(String name, int amount, int price, String date) - создает продажи

-Application.demandGET() - возвращает продажи

-Application.salesreportGET(String name, String date) - рассчитывает прибыль

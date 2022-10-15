
public class MySolver {  // Класс, умеющий решать задачу подбором цифр в мат. выражении
    public static String Solve(String income){  // Главный метод
        income = income.replaceAll("\\s+","");  // чистим пробелы
        String[] myList = income.split("="); // разбиваем строку на мат. выражение и ответ по символу =
        int operation = 0;  // код операции
        //System.out.println(myList[0]);
        if (myList[0].contains("+")) {operation = 1;} // вот такие коды
        if (myList[0].contains("-")) {operation = 2;}
        if (myList[0].contains("*")) {operation = 3;}
        if (operation == 0) {  // когда ничего не нашли
            return "Ошибка: Неверное выражение";
        }
        String op = "";  // для красивого вывода результата в конце
        switch (operation) {
            case (1): op = "\\+"; break;
            case (2): op = "-"; break;
            case (3): op = "\\*"; break;
            default: op = ""; break;
        }
        String[] myOperands = myList[0].split(op); // делим мат. выражение на операнды
        String leftOperand ="";
        String rightOperand ="";
        boolean flag = false;
        try {  // вдруг строка некорректно задана
            for (int i = 0; i < 10; i++) {  // решаем перебором всех возможных значений
                for (int j = 0; j < 10; j++) {
                    leftOperand = myOperands[0].replaceAll("\\?", Integer.toString(i)); // меняем ? на цифру
                    rightOperand = myOperands[1].replaceAll("\\?", Integer.toString(j));
                    switch (operation) {  // проверка вычислений
                        case (1):
                            if (Integer.parseInt(leftOperand) + Integer.parseInt(rightOperand) == Integer.parseInt(myList[1])) {
                                flag = true;
                            }
                            break;
                        case (2):
                            if (Integer.parseInt(leftOperand) - Integer.parseInt(rightOperand) == Integer.parseInt(myList[1])) {
                                flag = true;
                            }
                            break;
                        case (3):
                            if (Integer.parseInt(leftOperand) * Integer.parseInt(rightOperand) == Integer.parseInt(myList[1])) {
                                flag = true;
                            }
                            break;
                    }
                    if (flag) {
                        // решение найдено
                        return (leftOperand + " " + op + " " + rightOperand + " = " + myList[1]).replaceAll("\\\\","");
                    }
                }
            }
        } catch (Exception e) {  // печатаем текст ошибки, если она была
            System.out.println(e.getMessage());
        }
        // ничего не подобрали
        return "Не имеет решения";
    }
}

package com.example.lewis.sensorlogger;

public class SQLStringTableGenerator {
    private static StringBuilder sqlCommand;
    private static SensorTable table;

    public static String generate(SensorTable table) {
        SQLStringTableGenerator.table = table;
        sqlCommand = new StringBuilder();

        createTableCommand();
        createColumnParameters();

        return sqlCommand.toString();
    }

    private static void createTableCommand() {
        sqlCommand.append("create table ");
        sqlCommand.append(table.name);
    }

    private static void createColumnParameters() {

        sqlCommand.append("( ");
        createFirstColumnParameter();
        createRemainingColumnParametersWithoutCommaAtEnd();
        sqlCommand.append(")");
    }

    private static void createFirstColumnParameter() {
        SQLColumn firstColumn = table.columns[0];
        sqlCommand.append(firstColumn.name);
        sqlCommand.append(" ");
        sqlCommand.append(firstColumn.dataType);
    }

    private static void createRemainingColumnParametersWithoutCommaAtEnd() {
        int numberOfColumns = table.columns.length;

        for (int columnIndex = 1; columnIndex < numberOfColumns; ++columnIndex) {
            SQLColumn column = table.columns[columnIndex];
            sqlCommand.append(", ");
            sqlCommand.append(column.name);
            sqlCommand.append(" ");
            sqlCommand.append(column.dataType);
        }
    }
}

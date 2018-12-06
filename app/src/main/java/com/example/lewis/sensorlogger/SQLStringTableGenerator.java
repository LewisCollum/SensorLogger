package com.example.lewis.sensorlogger;

public class SQLStringTableGenerator {
    private static StringBuilder sqlCommand;
    private static String tableName;
    private static SQLColumn[] columns;

    public static String generate(String tableName, SQLColumn[] columns) {
        sqlCommand = new StringBuilder();
        SQLStringTableGenerator.tableName = tableName;
        SQLStringTableGenerator.columns = columns;

        createTableCommand();
        createColumnParameters();

        return sqlCommand.toString();
    }

    private static void createTableCommand() {
        sqlCommand.append("create table ");
        sqlCommand.append(tableName);
    }

    private static void createColumnParameters() {

        sqlCommand.append("( ");
        createFirstColumnParameter();
        createRemainingColumnParametersWithoutCommaAtEnd();
        sqlCommand.append(")");
    }

    private static void createFirstColumnParameter() {
        SQLColumn firstColumn = columns[0];
        sqlCommand.append(firstColumn.name);
        sqlCommand.append(" ");
        sqlCommand.append(firstColumn.dataType);
    }

    private static void createRemainingColumnParametersWithoutCommaAtEnd() {
        int numberOfColumns = columns.length;

        for (int columnIndex = 1; columnIndex < numberOfColumns; ++columnIndex) {
            SQLColumn column = columns[columnIndex];
            sqlCommand.append(", ");
            sqlCommand.append(column.name);
            sqlCommand.append(" ");
            sqlCommand.append(column.dataType);
        }
    }
}

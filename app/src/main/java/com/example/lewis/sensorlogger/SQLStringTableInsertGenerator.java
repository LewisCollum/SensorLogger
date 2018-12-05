package com.example.lewis.sensorlogger;

public class SQLStringTableInsertGenerator {
    private static StringBuilder sqlCommand;
    private static String tableName;
    private static SensorSample sample;

    public static String generate(SensorSample sample, String tableName) {
        sqlCommand = new StringBuilder();
        SQLStringTableInsertGenerator.tableName = tableName;
        SQLStringTableInsertGenerator.sample = sample;

        createInsertCommand();
        createInsertParameters();

        return sqlCommand.toString();
    }

    private static void createInsertCommand() {
        sqlCommand.append("insert into ");
        sqlCommand.append(tableName);
        sqlCommand.append(" values");
    }

    private static void createInsertParameters() {
        int numberOfValues = sample.values.length;

        sqlCommand.append("(");
        sqlCommand.append(sample.values[0]);
        for (int valueIndex = 1; valueIndex < numberOfValues; ++valueIndex) {
            sqlCommand.append(", ");
            sqlCommand.append(sample.values[valueIndex]);
        }
        sqlCommand.append(")");
    }
}

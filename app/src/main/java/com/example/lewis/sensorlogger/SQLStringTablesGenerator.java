package com.example.lewis.sensorlogger;

public class SQLStringTablesGenerator {
    private static String[] sqlCommands;

    public static String[] generate(SensorTable[] tables) {
        sqlCommands = new String[tables.length];

        for (int tableIndex = 0; tableIndex < tables.length; ++tableIndex) {
            String sqlTableCommand = SQLStringTableGenerator.generate(tables[tableIndex]);
            sqlCommands[tableIndex] = sqlTableCommand;
        }

        return sqlCommands;
    }
}

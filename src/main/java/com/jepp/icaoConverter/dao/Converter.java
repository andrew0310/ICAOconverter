package com.jepp.icaoConverter.dao;

import com.jepp.icaoConverter.model.ICAOcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Converter {

    public static Optional<List<String>> convertGivenWord(String wordInput, String type) {

        List<String> convertedCodes = new ArrayList<>();

        String[] letterTable = wordInput.split("");

        for (String output : letterTable) {

            Optional<ICAOcode> icaoCodeOptional = ICAOcodeDAO.getCodeFromDatabase(output, type);
            if (icaoCodeOptional.isPresent()) {
                ICAOcode icaoCode = icaoCodeOptional.get();
                convertedCodes.add(icaoCode.toString());
                return Optional.of(convertedCodes);
            }
        }
        return Optional.empty();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_fuzzy_logic_medicina;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.LinguisticTerm;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import org.antlr.runtime.RecognitionException;

/**
 *
 * @author rayma
 */
public class BaseExcessDisorders {

    /**
     * @param args the command line arguments
     */
    public FIS fis;
    public void resetFis() throws RecognitionException{
        //\src\java\test_fuzzy_logic_medicina
        //String fileName = "src"+File.separator+"java"+File.separator+"test_fuzzy_logic_medicina"+File.separator+"acid_base_disturbances.fcl";
        //String fileName = "acid_base_disturbances.fcl";
        String stringFIS = "FUNCTION_BLOCK calculate_level_01\n" +
"// Define input variables\n" +
"VAR_INPUT\n" +
"    ph : REAL;\n" +
"END_VAR\n" +
"\n" +
"// Define output variable\n" +
"VAR_OUTPUT\n" +
"    acidosis: REAL;\n" +
"    alkalosis: REAL;\n" +
"END_VAR\n" +
"\n" +
"FUZZIFY ph\n" +
"    TERM low := (6,1) (6.5,0.9) (7.33,0.8) (7.4,0) ;\n" +
"    TERM normal := gauss 7.4 0.05;\n" +
"    TERM high := (7.4,0) (7.5,0.8) (8.5,0.9) (9,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"// Defuzzify output variables\n" +
"\n" +
"DEFUZZIFY acidosis			\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"DEFUZZIFY alkalosis			\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"RULEBLOCK No1\n" +
"    // Use 'min' for 'and' (also implicit use 'max'\n" +
"    // for 'or' to fulfill DeMorgan's Law)\n" +
"    AND : MIN;\n" +
"    // Use 'min' activation method\n" +
"    ACT : MIN;\n" +
"    // Use 'max' accumulation method\n" +
"    ACCU : MAX;\n" +
"\n" +
"                RULE 1 : IF ph IS low\n" +
"                        THEN acidosis IS very_likely;\n" +
"                RULE 2 : IF ph IS high\n" +
"                        THEN acidosis IS unlikely;\n" +
"                RULE 3 : IF ph IS high\n" +
"                        THEN alkalosis IS very_likely;\n" +
"                RULE 4 : IF ph IS low\n" +
"                        THEN alkalosis IS unlikely;\n" +
"\n" +
"END_RULEBLOCK\n" +
"\n" +
"END_FUNCTION_BLOCK\n" +
"\n" +
"//********************************************************//\n" +
"\n" +
"\n" +
"FUNCTION_BLOCK calculate_level_02\n" +
"\n" +
"// Define input variables\n" +
"VAR_INPUT\n" +
"    acidosis : REAL;\n" +
"    alkalosis: REAL;\n" +
"    pco2: REAL;\n" +
"END_VAR\n" +
"\n" +
"// Define output variable\n" +
"VAR_OUTPUT\n" +
"    respiratory_acidosis: REAL;\n" +
"    expected_metabolic_acidosis: REAL;\n" +
"    respiratory_alkalosis: REAL;\n" +
"    expected_metabolic_alkalosis: REAL;\n" +
"END_VAR\n" +
"\n" +
"FUZZIFY acidosis			\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY alkalosis			\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY pco2\n" +
"    TERM low := (0,1) (32,0.5) (43,0);\n" +
"    TERM normal := gbell 6.5 8 40;\n" +
"    TERM high := (37,0) (45,0.5) (95,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"// Defuzzify output variables\n" +
"\n" +
"DEFUZZIFY respiratory_acidosis			\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"DEFUZZIFY expected_metabolic_acidosis			\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"DEFUZZIFY respiratory_alkalosis		\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"DEFUZZIFY expected_metabolic_alkalosis			\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"RULEBLOCK No1\n" +
"    // Use 'min' for 'and' (also implicit use 'max'\n" +
"    // for 'or' to fulfill DeMorgan's Law)\n" +
"    AND : MIN;\n" +
"    // Use 'min' activation method\n" +
"    ACT : MIN;\n" +
"    // Use 'max' accumulation method\n" +
"    ACCU : MAX;\n" +
"\n" +
"                RULE 1 : IF acidosis IS very_likely and pco2 is high\n" +
"                        THEN respiratory_acidosis IS very_likely;\n" +
"\n" +
"                RULE 2 : IF acidosis IS very_likely and pco2 is low\n" +
"                        THEN expected_metabolic_acidosis IS very_likely;\n" +
"                \n" +
"                RULE 3 : IF alkalosis IS very_likely and pco2 is low\n" +
"                        THEN respiratory_alkalosis IS very_likely;\n" +
"\n" +
"                RULE 4 : IF alkalosis IS very_likely and pco2 is high\n" +
"                        THEN expected_metabolic_alkalosis IS very_likely;\n" +
"\n" +
"                RULE 5 : IF acidosis IS unlikely and pco2 is normal\n" +
"                        THEN respiratory_acidosis IS unlikely;\n" +
"\n" +
"                RULE 6 : IF acidosis IS unlikely and pco2 is normal\n" +
"                        THEN expected_metabolic_acidosis IS unlikely;\n" +
"                \n" +
"                RULE 7 : IF alkalosis IS unlikely and pco2 is normal\n" +
"                        THEN respiratory_alkalosis IS unlikely;\n" +
"\n" +
"                RULE 8 : IF alkalosis IS unlikely and pco2 is normal\n" +
"                        THEN expected_metabolic_alkalosis IS unlikely;\n" +
"\n" +
"END_RULEBLOCK\n" +
"\n" +
"END_FUNCTION_BLOCK\n" +
"\n" +
"\n" +
"//********************************************************//\n" +
"\n" +
"FUNCTION_BLOCK calculate_level_03\n" +
"\n" +
"// Define input variables\n" +
"VAR_INPUT\n" +
"    respiratory_acidosis: REAL;\n" +
"    expected_metabolic_acidosis: REAL;\n" +
"    respiratory_alkalosis: REAL;\n" +
"    expected_metabolic_alkalosis: REAL;\n" +
"    sbe: REAL;\n" +
"END_VAR\n" +
"\n" +
"// Define output variable\n" +
"VAR_OUTPUT\n" +
"    acute_respiratory_acidosis: REAL;\n" +
"    chronic_respiratory_acidosis: REAL;\n" +
"    metabolic_acidosis: REAL;\n" +
"    acute_respiratory_alkalosis: REAL;\n" +
"    chronic_respiratory_alkalosis: REAL;\n" +
"    metabolic_alkalosis: REAL;\n" +
"END_VAR\n" +
"\n" +
"FUZZIFY respiratory_acidosis			\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY expected_metabolic_acidosis			\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY respiratory_alkalosis			\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY expected_metabolic_alkalosis			\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY sbe\n" +
"    TERM low := (-60,1) (-2,0.5) (0.5,0);\n" +
"    TERM normal := gbell 2.5 6 0.5;\n" +
"    TERM high := (0.5,0) (3,0.5) (60,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"// Defuzzify output variables\n" +
"\n" +
"DEFUZZIFY acute_respiratory_acidosis		\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"DEFUZZIFY chronic_respiratory_acidosis		\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"DEFUZZIFY metabolic_acidosis	\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"DEFUZZIFY acute_respiratory_alkalosis		\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"DEFUZZIFY chronic_respiratory_alkalosis	\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"DEFUZZIFY metabolic_alkalosis		\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"RULEBLOCK No1\n" +
"    // Use 'min' for 'and' (also implicit use 'max'\n" +
"    // for 'or' to fulfill DeMorgan's Law)\n" +
"    AND : MIN;\n" +
"    // Use 'min' activation method\n" +
"    ACT : MIN;\n" +
"    // Use 'max' accumulation method\n" +
"    ACCU : MAX;\n" +
"\n" +
"                RULE 1 : IF respiratory_acidosis IS very_likely and sbe is normal\n" +
"                        THEN acute_respiratory_acidosis IS very_likely;\n" +
"\n" +
"                RULE 2 : IF respiratory_acidosis IS very_likely and sbe is high\n" +
"                        THEN chronic_respiratory_acidosis IS very_likely;\n" +
"                \n" +
"                RULE 3 : IF expected_metabolic_acidosis IS very_likely and sbe is low\n" +
"                        THEN metabolic_acidosis IS very_likely;\n" +
"\n" +
"                RULE 4 : IF respiratory_alkalosis IS very_likely and sbe is normal\n" +
"                        THEN acute_respiratory_alkalosis IS very_likely;\n" +
"                \n" +
"                RULE 5 : IF respiratory_alkalosis IS very_likely and sbe is low\n" +
"                        THEN chronic_respiratory_alkalosis IS very_likely;\n" +
"\n" +
"                RULE 6 : IF expected_metabolic_alkalosis IS very_likely and sbe is high\n" +
"                        THEN metabolic_alkalosis IS very_likely;\n" +
"\n" +
"                RULE 7 : IF respiratory_acidosis IS unlikely and sbe is low\n" +
"                        THEN acute_respiratory_acidosis IS unlikely;\n" +
"\n" +
"                RULE 8 : IF respiratory_acidosis IS unlikely and sbe is normal\n" +
"                        THEN chronic_respiratory_acidosis IS unlikely;\n" +
"                \n" +
"                RULE 9 : IF expected_metabolic_acidosis IS unlikely and sbe is low\n" +
"                        THEN metabolic_acidosis IS unlikely;\n" +
"\n" +
"                RULE 10 : IF respiratory_alkalosis IS unlikely and sbe is high\n" +
"                        THEN acute_respiratory_alkalosis IS unlikely;\n" +
"                \n" +
"                RULE 11 : IF respiratory_alkalosis IS unlikely and sbe is normal\n" +
"                        THEN chronic_respiratory_alkalosis IS unlikely;\n" +
"\n" +
"                RULE 12 : IF expected_metabolic_alkalosis IS unlikely and sbe is normal\n" +
"                        THEN metabolic_alkalosis IS unlikely;\n" +
"                        \n" +
"                        \n" +
"END_RULEBLOCK\n" +
"\n" +
"END_FUNCTION_BLOCK\n" +
"\n" +
"//********************************************************//\n" +
"FUNCTION_BLOCK calculate_level_04\n" +
"\n" +
"// Define input variables\n" +
"VAR_INPUT\n" +
"   \n" +
"    compare_sbe_min: REAL;\n" +
"    compare_sbe_max: REAL;\n" +
"\n" +
"    compare_dhco3_min: REAL;\n" +
"    compare_dhco3_max: REAL;\n" +
"\n" +
"    compare_pco2_min: REAL;\n" +
"    compare_pco2_max: REAL;\n" +
"    \n" +
"    compare_dpco2_min: REAL;\n" +
"    compare_dpco2_max: REAL;\n" +
"    \n" +
"END_VAR\n" +
"\n" +
"// Define output variable\n" +
"VAR_OUTPUT\n" +
"    metabolic_compensatory_response: REAL;\n" +
"    respiratory_compensatory_response: REAL;\n" +
"\n" +
"END_VAR\n" +
"\n" +
"FUZZIFY compare_pco2_min\n" +
"    TERM less := (-100,1) (-7.8,1) (-2.6,0.8) (0,0);\n" +
"    TERM equal := gbell 2.6 5.2 0;\n" +
"    TERM greather := (0,0) (2.6,0.8) (7.8,1) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY compare_pco2_max\n" +
"    TERM less := (-100,1) (-7.8,1) (-2.6,0.8) (0,0);\n" +
"    TERM equal := gbell 2.6 5.2 0;\n" +
"    TERM greather := (0,0) (2.6,0.8) (7.8,1) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY compare_dpco2_min\n" +
"    TERM less := (-100,1) (-7.8,1) (-2.6,0.8) (0,0);\n" +
"    TERM equal := gbell 2.6 5.2 0;\n" +
"    TERM greather := (0,0) (2.6,0.8) (7.8,1) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY compare_dpco2_max\n" +
"    TERM less := (-100,1) (-7.8,1) (-2.6,0.8) (0,0);\n" +
"    TERM equal := gbell 2.6 5.2 0;\n" +
"    TERM greather := (0,0) (2.6,0.8) (7.8,1) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY compare_dhco3_min\n" +
"    TERM less := (-100,1) (-9,1) (-3,0.8) (0,0);\n" +
"    TERM equal := gbell 3 6 0;\n" +
"    TERM greather := (0,0) (3,0.8) (9,1) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY compare_dhco3_max\n" +
"    TERM less := (-100,1) (-9,1) (-3,0.8) (0,0);\n" +
"    TERM equal := gbell 3 6 0;\n" +
"    TERM greather := (0,0) (3,0.8) (9,1) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"\n" +
"FUZZIFY compare_sbe_min\n" +
"    TERM less := (-20,1) (-2.4,1) (-0.8,0.8) (0,0);\n" +
"    TERM equal := gbell 2.5 3 0;\n" +
"    TERM greather := (0,0) (0.8,0.8) (2.4,1) (20,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY compare_sbe_max\n" +
"    TERM less := (-20,1) (-2.4,1) (-0.8,0.8) (0,0);\n" +
"    TERM equal := gbell 2.5 3 0;\n" +
"    TERM greather := (0,0) (0.8,0.8) (2.4,1) (20,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"// Defuzzify output variables\n" +
"\n" +
"DEFUZZIFY metabolic_compensatory_response\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"DEFUZZIFY respiratory_compensatory_response\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"RULEBLOCK No1\n" +
"    // Use 'min' for 'and' (also implicit use 'max'\n" +
"    // for 'or' to fulfill DeMorgan's Law)\n" +
"    AND : MIN;\n" +
"    // Use 'min' activation method\n" +
"    ACT : MIN;\n" +
"    // Use 'max' accumulation method\n" +
"    ACCU : MAX;\n" +
"\n" +
"        RULE 1 : IF ((compare_sbe_min IS greather OR compare_sbe_min IS equal) AND (compare_sbe_max IS less OR compare_sbe_max IS equal))\n" +
"                OR ((compare_dhco3_min IS greather OR compare_dhco3_min IS equal) AND (compare_dhco3_max IS less OR compare_dhco3_max IS equal))\n" +
"                THEN metabolic_compensatory_response IS very_likely;\n" +
"        \n" +
"        RULE 2 : IF (compare_sbe_min IS less OR compare_sbe_max IS greather)\n" +
"                AND (compare_dhco3_min IS less OR compare_dhco3_max IS greather)\n" +
"                THEN metabolic_compensatory_response IS unlikely;\n" +
"        \n" +
"        RULE 3 : IF ((compare_pco2_min IS greather OR compare_pco2_min IS equal) AND (compare_pco2_max IS less OR compare_pco2_max IS equal))\n" +
"                OR ((compare_dpco2_min IS greather OR compare_dpco2_min IS equal) AND (compare_dpco2_max IS less OR compare_dpco2_max IS equal))\n" +
"                THEN respiratory_compensatory_response IS very_likely;\n" +
"\n" +
"        RULE 4 : IF (compare_pco2_min IS less OR compare_pco2_max IS greather)\n" +
"                AND (compare_dpco2_min IS less OR compare_dpco2_max IS greather)\n" +
"                THEN respiratory_compensatory_response IS unlikely;\n" +
"\n" +
"END_RULEBLOCK\n" +
"\n" +
"END_FUNCTION_BLOCK\n" +
"\n" +
"//********************************************************//\n" +
"\n" +
"FUNCTION_BLOCK calculate_level_05\n" +
"\n" +
"// Define input variables\n" +
"VAR_INPUT\n" +
"   \n" +
"    compare_sbe_min: REAL;\n" +
"    compare_sbe_max: REAL;\n" +
"\n" +
"    compare_dhco3_min: REAL;\n" +
"    compare_dhco3_max: REAL;\n" +
"\n" +
"    compare_pco2_min: REAL;\n" +
"    compare_pco2_max: REAL;\n" +
"    \n" +
"    compare_dpco2_min: REAL;\n" +
"    compare_dpco2_max: REAL;\n" +
"    \n" +
"END_VAR\n" +
"\n" +
"// Define output variable\n" +
"VAR_OUTPUT\n" +
"    additional_respiratory_acidosis: REAL;\n" +
"    additional_respiratory_alkalosis: REAL;\n" +
"    additional_metabolic_acidosis: REAL;\n" +
"    additional_metabolic_alkalosis: REAL;\n" +
"END_VAR\n" +
"\n" +
"FUZZIFY compare_pco2_min\n" +
"    TERM less := (-100,1) (-7.8,1) (-2.6,0.8) (0,0);\n" +
"    TERM equal := gbell 2.6 5.2 0;\n" +
"    TERM greather := (0,0) (2.6,0.8) (7.8,1) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY compare_pco2_max\n" +
"    TERM less := (-100,1) (-7.8,1) (-2.6,0.8) (0,0);\n" +
"    TERM equal := gbell 2.6 5.2 0;\n" +
"    TERM greather := (0,0) (2.6,0.8) (7.8,1) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY compare_dpco2_min\n" +
"    TERM less := (-100,1) (-7.8,1) (-2.6,0.8) (0,0);\n" +
"    TERM equal := gbell 2.6 5.2 0;\n" +
"    TERM greather := (0,0) (2.6,0.8) (7.8,1) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY compare_dpco2_max\n" +
"    TERM less := (-100,1) (-7.8,1) (-2.6,0.8) (0,0);\n" +
"    TERM equal := gbell 2.6 5.2 0;\n" +
"    TERM greather := (0,0) (2.6,0.8) (7.8,1) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY compare_dhco3_min\n" +
"    TERM less := (-100,1) (-9,1) (-3,0.8) (0,0);\n" +
"    TERM equal := gbell 3 6 0;\n" +
"    TERM greather := (0,0) (3,0.8) (9,1) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY compare_dhco3_max\n" +
"    TERM less := (-100,1) (-9,1) (-3,0.8) (0,0);\n" +
"    TERM equal := gbell 3 6 0;\n" +
"    TERM greather := (0,0) (3,0.8) (9,1) (100,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY compare_sbe_min\n" +
"    TERM less := (-20,1) (-2.4,1) (-0.8,0.8) (0,0);\n" +
"    TERM equal := gbell 2.5 3 0;\n" +
"    TERM greather := (0,0) (0.8,0.8) (2.4,1) (20,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"FUZZIFY compare_sbe_max\n" +
"    TERM less := (-20,1) (-2.4,1) (-0.8,0.8) (0,0);\n" +
"    TERM equal := gbell 2.5 3 0;\n" +
"    TERM greather := (0,0) (0.8,0.8) (2.4,1) (20,1);\n" +
"END_FUZZIFY\n" +
"\n" +
"// Defuzzify output variables\n" +
"DEFUZZIFY additional_respiratory_acidosis\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"DEFUZZIFY additional_respiratory_alkalosis			\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"DEFUZZIFY additional_metabolic_acidosis			\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"DEFUZZIFY additional_metabolic_alkalosis\n" +
"    TERM unlikely := (0, 1) (50,0);\n" +
"    TERM probable := (25, 0) (50, 1) (75,0);\n" +
"    TERM very_likely := (50, 0) (100,1);\n" +
"    // Use 'Center Of Gravity' defuzzification method\n" +
"    METHOD : COG;\n" +
"    // Default value is 0 (if no rule activates defuzzifier)\n" +
"    DEFAULT := 0;\n" +
"END_DEFUZZIFY\n" +
"\n" +
"RULEBLOCK No1\n" +
"    // Use 'min' for 'and' (also implicit use 'max'\n" +
"    // for 'or' to fulfill DeMorgan's Law)\n" +
"    AND : MIN;\n" +
"    // Use 'min' activation method\n" +
"    ACT : MIN;\n" +
"    // Use 'max' accumulation method\n" +
"    ACCU : MAX;\n" +
"\n" +
"        RULE 1 : IF compare_sbe_min IS less AND compare_dhco3_min IS less \n" +
"                THEN additional_metabolic_acidosis IS very_likely;\n" +
"        \n" +
"        RULE 2 : IF compare_sbe_min IS greather AND compare_dhco3_max IS greather \n" +
"                THEN additional_metabolic_alkalosis IS very_likely;\n" +
"               \n" +
"        RULE 3 : IF compare_pco2_min IS less OR compare_dpco2_min IS less\n" +
"                THEN additional_respiratory_alkalosis IS very_likely;\n" +
"        \n" +
"        RULE 4 : IF compare_pco2_max IS greather OR compare_dpco2_max IS greather\n" +
"                THEN additional_respiratory_acidosis IS very_likely;\n" +
"\n" +
"END_RULEBLOCK\n" +
"\n" +
"END_FUNCTION_BLOCK\n" +
"";
        fis=FIS.createFromString(stringFIS, true);
        /*fis = FIS.load(fileName,true);
        // Error while loading?
        if( fis == null ) { 
            System.err.println("Can't load file: '" 
                                   + fileName + "'");
        }*/
    }
    public static void main(String[] args) throws RecognitionException {
        // TODO code application logic here
        BaseExcessDisorders bed=new BaseExcessDisorders();
        bed.resetFis();
        double[][] cases={
            {7.21,23,9,-8},//1
            {7.3,16,10,-8},//2
            {7.1,32,11,-5},//3 
            {7.28,74,34,12},//4
            {7.56,47,38,5}, //5
            {7.58,22,20,-3},//6
            {7.26,16,12,-8},//7
            {7.08,40,10,-9},//8
            {7.49,43,31,7},//9
            {7.26,82,32,8},//10
            {7.19,16.3,6,-21.2},//11
            {7.08,40,10,-9},//12
            {7.46,51,34,6},//13
            {7.28,34,14,-5},//14
            {7.51,46,33,6}//15
            };
        

        //fis.getFunctionBlock("calculate_level_01").chart();
        CPossibleDiagnose firstLevel;
        CPossibleDiagnose secondLevel;
        
        CPossibleDiagnose primaryDiagnose;
        CPossibleDiagnose compensatoryResponse;
        CPossibleDiagnose secondaryDiagnose;
        
        /*for (int i = 0; i < 15; i++) {
            System.out.println("case: "+(i+1)+" =================================");
            firstLevel=calculateLevel01(cases[i][0]);
            System.out.println(firstLevel);
            
            secondLevel=calculateLevel02(cases[i][1]);
            System.out.println(secondLevel);
            
            primaryDiagnose=calculateLevel03(cases[i][3]);
            System.out.println(primaryDiagnose);
            compensatoryResponse=calculateLevel04(cases[i][0], cases[i][1], cases[i][3], cases[i][2],primaryDiagnose.getDiagnose());
            System.out.println(compensatoryResponse);
            if(compensatoryResponse.getProbability()<50){
                secondaryDiagnose=calculateLevel05(cases[i][0], cases[i][1], cases[i][3], cases[i][2],primaryDiagnose.getDiagnose());
                System.out.println(secondaryDiagnose);
            }
            
            resetFis();
            System.out.println("*************");
        }*/
        
        List diagnosisList=bed.listDiagnosis(7.21,23,9,-8);
        System.out.println(diagnosisList);
        
        
        //fis.getFunctionBlock("calculate_level_03").chart();
        
    }
    public List<CPossibleDiagnose> listDiagnosis(double ph, double  pco2,double sbe,double hco3){
        List<CPossibleDiagnose> diagnosisList=new ArrayList();
        CPossibleDiagnose primaryDiagnose;
        CPossibleDiagnose compensatoryResponse;
        CPossibleDiagnose secondaryDiagnose=new CPossibleDiagnose("", 0, "", 0);
        
        diagnosisList.add(calculateLevel01(ph));
        diagnosisList.add(calculateLevel02(pco2));
        
        primaryDiagnose=calculateLevel03(sbe);
        diagnosisList.add(primaryDiagnose);
        
        compensatoryResponse=calculateLevel04(ph, pco2, sbe, hco3, primaryDiagnose.getDiagnose());
        diagnosisList.add(compensatoryResponse);
        
        if(compensatoryResponse.getProbability()<50){
            secondaryDiagnose=calculateLevel05(ph, pco2, sbe, hco3, primaryDiagnose.getDiagnose());
            
        }
        diagnosisList.add(secondaryDiagnose);
        
        return diagnosisList;
    }
    public CPossibleDiagnose calculateLevel01(double ph){
        fis.getFunctionBlock("calculate_level_01").setVariable("ph", ph);
        return calculateDisturbance("calculate_level_01");
        
    }
    public CPossibleDiagnose calculateLevel02(double pco2){
        double acidosis=fis.getFunctionBlock("calculate_level_01").getVariable("acidosis").getValue();
        double alkalosis=fis.getFunctionBlock("calculate_level_01").getVariable("alkalosis").getValue();
        fis.setVariable("calculate_level_02","acidosis",acidosis);
        fis.setVariable("calculate_level_02","alkalosis",alkalosis);
        fis.setVariable("calculate_level_02","pco2",pco2);
        return calculateDisturbance("calculate_level_02");
    }
    public CPossibleDiagnose calculateLevel03(double sbe){
        double respiratory_acidosis=fis.getFunctionBlock("calculate_level_02").getVariable("respiratory_acidosis").getValue();
        double expected_metabolic_acidosis=fis.getFunctionBlock("calculate_level_02").getVariable("expected_metabolic_acidosis").getValue();
        double respiratory_alkalosis=fis.getFunctionBlock("calculate_level_02").getVariable("respiratory_alkalosis").getValue();
        double expected_metabolic_alkalosis=fis.getFunctionBlock("calculate_level_02").getVariable("expected_metabolic_alkalosis").getValue();
        
        fis.setVariable("calculate_level_03","respiratory_acidosis",respiratory_acidosis);
        fis.setVariable("calculate_level_03","expected_metabolic_acidosis",expected_metabolic_acidosis);
        fis.setVariable("calculate_level_03","respiratory_alkalosis",respiratory_alkalosis);
        fis.setVariable("calculate_level_03","expected_metabolic_alkalosis",expected_metabolic_alkalosis);
        fis.setVariable("calculate_level_03","sbe",sbe);
        return calculateDisturbance("calculate_level_03");
    }
    public CPossibleDiagnose calculateLevel04(double ph, double  pco2,double sbe,double hco3,String primaryDiagnose){
        double dhco3=hco3-20;
        double dpco2=pco2-40;
        double sbe_min=100,sbe_max=100;
        double dhco3_min=100,dhco3_max=100;
        double pco2_min=100,pco2_max=100;
        double dpco2_min=100,dpco2_max=100;
                
        switch (primaryDiagnose) {
            case "acute_respiratory_acidosis":
                sbe_min=-2;
                dhco3_min=40;
                sbe_max=2;
                dhco3_max=10*dpco2;
                break;
            case "acute_respiratory_alkalosis":
                sbe_min=-2;
                dhco3_min=5*dpco2;
                sbe_max=2;
                dhco3_max=20;
                break;
            case "chronic_respiratory_acidosis":
                sbe_min=0.4*(pco2-40);
                dhco3_min=2*dpco2;
                sbe_max=0.4*(pco2-40);
                dhco3_max=2.5*dpco2;
                break;
            case "chronic_respiratory_alkalosis":
                sbe_min=0.4*(pco2-40);
                dhco3_min=2.5*dpco2;
                sbe_max=0.4*(pco2-40);
                dhco3_max=2*dpco2;
                break;
            case "metabolic_acidosis":
                dpco2_min=sbe;
                pco2_min=1.5*hco3+6;
                dpco2_max=sbe;
                pco2_max=1.5*hco3+10;
                break;
            default: //"metabolic_alkalosis":
                dpco2_min=0.6*sbe;
                pco2_min=0.7*(hco3-24)+38;
                dpco2_max=0.6*sbe;
                pco2_max=0.7*(hco3-24)+42;
                break;
        }
        if(primaryDiagnose.equals("metabolic_acidosis") || primaryDiagnose.equals("metabolic_alkalosis")){
            fis.setVariable("calculate_level_04","compare_dpco2_min",dpco2-dpco2_min);
            fis.setVariable("calculate_level_04","compare_pco2_min",pco2-pco2_min);
            fis.setVariable("calculate_level_04","compare_dpco2_max",dpco2-dpco2_max);
            fis.setVariable("calculate_level_04","compare_pco2_max",pco2-pco2_max);
        }else{
            fis.setVariable("calculate_level_04","compare_sbe_min",sbe-sbe_min);
            fis.setVariable("calculate_level_04","compare_dhco3_min",dhco3-dhco3_min);
            fis.setVariable("calculate_level_04","compare_sbe_max",sbe-sbe_max);
            fis.setVariable("calculate_level_04","compare_dhco3_max",dhco3-dhco3_max);
        }
        
        return calculateDisturbance("calculate_level_04");
    }
    public CPossibleDiagnose calculateLevel05(double ph, double  pco2,double sbe,double hco3,String primaryDiagnose){
        double dhco3=hco3-20;
        double dpco2=pco2-40;
        double sbe_min=100,sbe_max=100;
        double dhco3_min=100,dhco3_max=100;
        double pco2_min=100,pco2_max=100;
        double dpco2_min=100,dpco2_max=100;
                
        switch (primaryDiagnose) {
            case "acute_respiratory_acidosis":
                sbe_min=-2;
                dhco3_min=40;
                sbe_max=2;
                dhco3_max=10*dpco2;
                break;
            case "acute_respiratory_alkalosis":
                sbe_min=-2;
                dhco3_min=5*dpco2;
                sbe_max=2;
                dhco3_max=20;
                break;
            case "chronic_respiratory_acidosis":
                sbe_min=0.4*(pco2-40);
                dhco3_min=2*dpco2;
                sbe_max=0.4*(pco2-40);
                dhco3_max=2.5*dpco2;
                break;
            case "chronic_respiratory_alkalosis":
                sbe_min=0.4*(pco2-40);
                dhco3_min=2.5*dpco2;
                sbe_max=0.4*(pco2-40);
                dhco3_max=2*dpco2;
                break;
            case "metabolic_acidosis":
                dpco2_min=sbe;
                pco2_min=1.5*hco3+6;
                dpco2_max=sbe;
                pco2_max=1.5*hco3+10;
                break;
            default: //"metabolic_alkalosis":
                dpco2_min=0.6*sbe;
                pco2_min=0.7*(hco3-24)+38;
                dpco2_max=0.6*sbe;
                pco2_max=0.7*(hco3-24)+42;
                break;
        }
        if(primaryDiagnose.equals("metabolic_acidosis") || primaryDiagnose.equals("metabolic_alkalosis")){
            fis.setVariable("calculate_level_05","compare_dpco2_min",dpco2-dpco2_min);
            fis.setVariable("calculate_level_05","compare_pco2_min",pco2-pco2_min);
            fis.setVariable("calculate_level_05","compare_dpco2_max",dpco2-dpco2_max);
            fis.setVariable("calculate_level_05","compare_pco2_max",pco2-pco2_max);
        }else{
            fis.setVariable("calculate_level_05","compare_sbe_min",sbe-sbe_min);
            fis.setVariable("calculate_level_05","compare_dhco3_min",dhco3-dhco3_min);
            fis.setVariable("calculate_level_05","compare_sbe_max",sbe-sbe_max);
            fis.setVariable("calculate_level_05","compare_dhco3_max",dhco3-dhco3_max);
        }
        
        return calculateDisturbance("calculate_level_05");
    }
    
    
    public CPossibleDiagnose calculateDisturbance(String blockName) {
        CPossibleDiagnose cPossibleDiagnose;
        fis.getFunctionBlock(blockName).evaluate();
        String maxDisturbance="";
        double maxValue=0;
        Map<String,Variable> mapVariables=fis.getFunctionBlock(blockName).getVariables();
        for (Map.Entry<String, Variable> entry : mapVariables.entrySet()) {
            if(entry.getValue().isOutput()){
                if(entry.getValue().getValue()>maxValue){
                    maxValue=entry.getValue().getValue();
                    maxDisturbance=entry.getValue().getName();
                }
                    
            }
        }
        
        double max=0;
        double membership=0;
        String term="";
        for(LinguisticTerm l : fis.getFunctionBlock(blockName).getVariable(maxDisturbance).getLinguisticTerms().values() ){
            membership=fis.getFunctionBlock(blockName).getVariable(maxDisturbance).getMembership(l.getTermName());
            if(membership>max){
                max=membership;
                term=l.getTermName();
            }
        }
        
        cPossibleDiagnose=new CPossibleDiagnose(maxDisturbance, maxValue, term, membership);
        return cPossibleDiagnose;
        
    }
    
    
    
}

package com.vstu.department.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.vstu.department.dto.EmployeeDTO;

@Service
public class StubService {

    private static final Map<String, List<EmployeeDTO>> employees;

    static {
        employees = new HashMap<>();
        List<EmployeeDTO> diMEmployees = new ArrayList<>(
                Arrays.asList(new EmployeeDTO("АБРАМОВИЧ Н.А.", "зав. кафедрой ", "ДиМ", "DiM2"),
                        new EmployeeDTO("КАЗАРНОВСКАЯ Г.В.", "профессор ", "ДиМ", "DiM400"),
                        new EmployeeDTO("ВАСИЛЬЕВ В.В.", "доцент", "ДиМ", "DiM401"),
                        new EmployeeDTO("МАЛИН А.Г.", "доцент", "ДиМ", "DiM402"),
                        new EmployeeDTO("НАГОВИЦЫНА Т.В.", "доцент", "ДиМ", "DiM403"),
                        new EmployeeDTO("ПОПКОВСКАЯ Л.В.", "доцент", "ДиМ", "DiM404"),
                        new EmployeeDTO("САМУТИНА Н.Н.", "доцент", "ДиМ", "DiM405"),
                        new EmployeeDTO("ТАРАБУКО Н.И.", "доцент", "ДиМ", "DiM406"),
                        new EmployeeDTO("ГУДЧЕНКО О.Ф.", "доцент без ст. и зв.", "ДиМ", "DiM407"),
                        new EmployeeDTO("КИРИЛЛОВА И.Л.", "доцент без ст. и зв.", "ДиМ", "DiM408"),
                        new EmployeeDTO("ЛУЦЕЙКОВИЧ Т.Н.", "доцент без ст. и зв.", "ДиМ", "DiM409"),
                        new EmployeeDTO("МИНИН С.Н.", "доцент без ст. и зв.", "ДиМ", "DiM410"),
                        new EmployeeDTO("ПОПОВА А.В.", "доцент без ст. и зв.", "ДиМ", "DiM411"),
                        new EmployeeDTO("ТОЛОБОВА Е.О.", "доцент без ст. и зв.", "ДиМ", "DiM412"),
                        new EmployeeDTO("ГУРКО И.С.", "ст.преподаватель", "ДиМ", "DiM413"),
                        new EmployeeDTO("НЕКРАСОВА В.А.", "ст.преподаватель", "ДиМ", "DiM414"),
                        new EmployeeDTO("ОНУФРИЕНКО С.Г.", "ст.преподаватель", "ДиМ", "DiM415"),
                        new EmployeeDTO("УШКИНА И.М.", "ст.преподаватель", "ДиМ", "DiM416"),
                        new EmployeeDTO("ФЕДОРЕЦ В.П.", "ст.преподаватель", "ДиМ", "DiM417"),
                        new EmployeeDTO("ЛУКЬЯНЕНКО Е.А.", "препод.стажер ", "ДиМ", "DiM418"),
                        new EmployeeDTO("СОСНИНА А.М.", "препод.стажер ", "ДиМ", "DiM419"),
                        new EmployeeDTO("ВАСИЛЬЕВА Г.С.", "доцент", "ДиМ", "DiM420"),
                        new EmployeeDTO("ЛИСОВСКАЯ Н.С.", "доцент", "ДиМ", "DiM421"),
                        new EmployeeDTO("ОКСИНЬ С.А.", "доцент", "ДиМ", "DiM422"),
                        new EmployeeDTO("ФАЛЕЙ А.В.", "доцент без ст. и зв.", "ДиМ", "DiM423"),
                        new EmployeeDTO("КУКУРУЗА С.В.", "ст.преподаватель", "ДиМ", "DiM424")));

        List<EmployeeDTO> inYazEmployees = new ArrayList<>(
                Arrays.asList(new EmployeeDTO("КОСТЫРЕВА С.С.", "зав. кафедрой ", "ИнЯз", "InYaz569"),
                        new EmployeeDTO("СТЕПАНОВ Д.А.", "ст.преподаватель", "ИнЯз", "InYaz270"),
                        new EmployeeDTO("ИЗМАЙЛОВИЧ О.В.", "ст.преподаватель", "ИнЯз", "InYaz425"),
                        new EmployeeDTO("ИМПЕРОВИЧ В.В.", "ст.преподаватель", "ИнЯз", "InYaz426"),
                        new EmployeeDTO("БУРДЫКО О.В.", "преподаватель ", "ИнЯз", "InYaz427"),
                        new EmployeeDTO("ОЛЕФИРЕНКО В.Б.", "ст.преподаватель", "ИнЯз", "InYaz428"),
                        new EmployeeDTO("СЕРЕБРЯКОВА В.В.", "преподаватель ", "ИнЯз", "InYaz429"),
                        new EmployeeDTO("ПИОТУХ А.А.", "ст.преподаватель", "ИнЯз", "InYaz430")));

        List<EmployeeDTO> isapEmployees = new ArrayList<>(
                Arrays.asList(new EmployeeDTO("КАЗАКОВ В.Е.", "зав. кафедрой ", "ИСАП", "ISAP108"),
                        new EmployeeDTO("КОРНИЕНКО А.А.", "профессор ", "ИСАП", "ISAP431"),
                        new EmployeeDTO("БЕЛОВ А.А.", "доцент", "ИСАП", "ISAP432"),
                        new EmployeeDTO("БУЕВИЧ Т.В.", "доцент", "ИСАП", "ISAP433"),
                        new EmployeeDTO("ДУНИНА Е.Б.", "доцент", "ИСАП", "ISAP434"),
                        new EmployeeDTO("НАУМЕНКО А.М.", "доцент", "ИСАП", "ISAP435"),
                        new EmployeeDTO("НОВИКОВ Ю.В.", "доцент", "ИСАП", "ISAP436"),
                        new EmployeeDTO("БИЗЮК А.Н.", "ст.преподаватель", "ИСАП", "ISAP437"),
                        new EmployeeDTO("ГНИДЕНКО А.К.", "ст.преподаватель", "ИСАП", "ISAP438"),
                        new EmployeeDTO("ДЕРКАЧЕНКО П.Г.", "ст.преподаватель", "ИСАП", "ISAP439"),
                        new EmployeeDTO("КЛИМЕНКОВА С.А.", "ст.преподаватель", "ИСАП", "ISAP440"),
                        new EmployeeDTO("КУКСЕВИЧ В.Ф.", "ст.преподаватель", "ИСАП", "ISAP441"),
                        new EmployeeDTO("ЛЕОНОВ В.В.", "ст.преподаватель", "ИСАП", "ISAP442"),
                        new EmployeeDTO("СОКОЛОВА А.С.", "ст.преподаватель", "ИСАП", "ISAP443"),
                        new EmployeeDTO("ЧЕРНЕНКО Д.В.", "ст.преподаватель", "ИСАП", "ISAP444"),
                        new EmployeeDTO("ЧЕРНОВ Е.А.", "ст.преподаватель", "ИСАП", "ISAP445"),
                        new EmployeeDTO("СУНКУЕВ Б.С.", "профессор ", "ИСАП", "ISAP446"),
                        new EmployeeDTO("РАДКЕВИЧ А.В.", "ст.преподаватель", "ИСАП", "ISAP447"),
                        new EmployeeDTO("ТУМАНОВ В.С.", "ассистент ", "ИСАП", "ISAP448")));

        List<EmployeeDTO> kiTOiOEmployees = new ArrayList<>(
                Arrays.asList(new EmployeeDTO("БОДЯЛО Н.Н.", "зав. кафедрой ", "КиТОиО", "KiTOiO21"),
                        new EmployeeDTO("ГОРБАЧИК В.Е.", "профессор ", "КиТОиО", "KiTOiO449"),
                        new EmployeeDTO("БОРИСОВА Т.М.", "доцент", "КиТОиО", "KiTOiO450"),
                        new EmployeeDTO("ДОВЫДЕНКОВА В.П.", "доцент", "КиТОиО", "KiTOiO451"),
                        new EmployeeDTO("ЗИМИНА Е.Л.", "доцент", "КиТОиО", "KiTOiO452"),
                        new EmployeeDTO("МИЛЮШКОВА Ю.В.", "доцент", "КиТОиО", "KiTOiO453"),
                        new EmployeeDTO("УЛЬЯНОВА Н.В.", "доцент", "КиТОиО", "KiTOiO454"),
                        new EmployeeDTO("ФУРАШОВА С.Л.", "доцент", "КиТОиО", "KiTOiO455"),
                        new EmployeeDTO("АЛАХОВА С.С.", "ст.преподаватель", "КиТОиО", "KiTOiO456"),
                        new EmployeeDTO("БОНДАРЕВА Е.В.", "ст.преподаватель", "КиТОиО", "KiTOiO457"),
                        new EmployeeDTO("ИВАНОВА Н.Н.", "ст.преподаватель", "КиТОиО", "KiTOiO458"),
                        new EmployeeDTO("КУКУШКИНА Ю.М.", "ст.преподаватель", "КиТОиО", "KiTOiO459")));

        List<EmployeeDTO> miitEmployees = new ArrayList<>(
                Arrays.asList(new EmployeeDTO("НИКОНОВА Т.В.", "зав. кафедрой ", "МиИТ", "MiIT463"),
                        new EmployeeDTO("ДЖЕЖОРА А.А.", "доцент", "МиИТ", "MiIT74"),
                        new EmployeeDTO("ДМИТРИЕВ А.П.", "ст.преподаватель", "МиИТ", "MiIT460"),
                        new EmployeeDTO("ДЯГИЛЕВ А.С.", "доцент", "МиИТ", "MiIT461"),
                        new EmployeeDTO("КОСТИН П.А.", "доцент", "МиИТ", "MiIT462"),
                        new EmployeeDTO("РАССОХИНА И.М.", "доцент", "МиИТ", "MiIT464"),
                        new EmployeeDTO("ВАРДОМАЦКАЯ Е.Ю.", "ст.преподаватель", "МиИТ", "MiIT465"),
                        new EmployeeDTO("КОВАЛЕНКО А.В.", "ст.преподаватель", "МиИТ", "MiIT466"),
                        new EmployeeDTO("МАНДРИК О.Г.", "ст.преподаватель", "МиИТ", "MiIT467"),
                        new EmployeeDTO("РУБАНИК О.Е.", "ст.преподаватель", "МиИТ", "MiIT468"),
                        new EmployeeDTO("СТАСЕНЯ Т.П.", "ст.преподаватель", "МиИТ", "MiIT469")));

        List<EmployeeDTO> malpEmployees = new ArrayList<>();

        List<EmployeeDTO> mEmployees = new ArrayList<>(
                Arrays.asList(new EmployeeDTO("КАЛИНОВСКАЯ И.Н.", "доцент", "М", "M470"),
                        new EmployeeDTO("СКВОРЦОВ В.А.", "доцент", "М", "M471"),
                        new EmployeeDTO("СЫСОЕВ И.П.", "доцент", "М", "M472"),
                        new EmployeeDTO("БАБЕНЯ И.Г.", "ст.преподаватель", "М", "M473"),
                        new EmployeeDTO("ЗАЙЦЕВА О.В.", "зав. кафедрой ", "М", "M567")));

        List<EmployeeDTO> sozGumEmployees = new ArrayList<>(
                Arrays.asList(new EmployeeDTO("ЛУЧЕНКОВА Е.С.", "доцент", "СоцГум", "SozGum169"),
                        new EmployeeDTO("ХАДАНЕНАК В.М.", "зав. кафедрой ", "СоцГум", "SozGum291"),
                        new EmployeeDTO("МЯДЕЛЬ А.П.", "доцент", "СоцГум", "SozGum474"),
                        new EmployeeDTO("РУДКО Е.А.", "доцент", "СоцГум", "SozGum475"),
                        new EmployeeDTO("СУББОТИН А.А.", "доцент", "СоцГум", "SozGum476"),
                        new EmployeeDTO("УТКЕВИЧ О.И.", "доцент", "СоцГум", "SozGum477"),
                        new EmployeeDTO("ИСАЧЕНКО А.В.", "ст.преподаватель", "СоцГум", "SozGum478"),
                        new EmployeeDTO("ОСТАПИШИНА Л.О.", "ст.преподаватель", "СоцГум", "SozGum479")));

        List<EmployeeDTO> tritEmployees = new ArrayList<>(
                Arrays.asList(new EmployeeDTO("БУРКИН А.Н.", "зав. кафедрой ", "ТРиТ", "TRiT32"),
                        new EmployeeDTO("МАХОНЬ А.Н.", "доцент", "ТРиТ", "TRiT480"),
                        new EmployeeDTO("ШЕВЦОВА М.В.", "доцент", "ТРиТ", "TRiT481"),
                        new EmployeeDTO("ШЕРЕМЕТ Е.А.", "доцент", "ТРиТ", "TRiT482"),
                        new EmployeeDTO("БОРОЗНА В.Д.", "ст.преподаватель", "ТРиТ", "TRiT483"),
                        new EmployeeDTO("БУЛАНЧИКОВ И.А.", "ст.преподаватель", "ТРиТ", "TRiT484"),
                        new EmployeeDTO("КАРПУШЕНКО И.С.", "ст.преподаватель", "ТРиТ", "TRiT485"),
                        new EmployeeDTO("КОЗЛОВСКАЯ Л.Г.", "ст.преподаватель", "ТРиТ", "TRiT486"),
                        new EmployeeDTO("ЦОБАНОВА Н.В.", "ассистент ", "ТРиТ", "TRiT487"),
                        new EmployeeDTO("ПАНКЕВИЧ Д.К.", "доцент", "ТРиТ", "TRiT488"),
                        new EmployeeDTO("РАДЮК А.Н.", "ассистент ", "ТРиТ", "TRiT489")));

        List<EmployeeDTO> tiompEmployees = new ArrayList<>(
                Arrays.asList(new EmployeeDTO("ОЛЬШАНСКИЙ В.И.", "зав. кафедрой ", "ТиОМП", "TiOMP207"),
                        new EmployeeDTO("КЛИМЕНКОВ С.С.", "профессор ", "ТиОМП", "TiOMP490"),
                        new EmployeeDTO("ПЯТОВ В.В.", "профессор ", "ТиОМП", "TiOMP491"),
                        new EmployeeDTO("АЛЕКСЕЕВ И.С.", "доцент", "ТиОМП", "TiOMP492"),
                        new EmployeeDTO("БЕЛОВ Е.В.", "доцент", "ТиОМП", "TiOMP493"),
                        new EmployeeDTO("БЕЛЯКОВ Н.В.", "доцент", "ТиОМП", "TiOMP494"),
                        new EmployeeDTO("ДРЮКОВ В.В.", "доцент", "ТиОМП", "TiOMP495"),
                        new EmployeeDTO("ЖЕРНОСЕК С.В.", "доцент", "ТиОМП", "TiOMP496"),
                        new EmployeeDTO("КОВЧУР А.С.", "доцент", "ТиОМП", "TiOMP497"),
                        new EmployeeDTO("МАХАРИНСКИЙ Ю.Е.", "доцент", "ТиОМП", "TiOMP498"),
                        new EmployeeDTO("ПУТЕЕВ Н.В.", "доцент", "ТиОМП", "TiOMP499"),
                        new EmployeeDTO("САВИЦКИЙ В.В.", "доцент", "ТиОМП", "TiOMP500"),
                        new EmployeeDTO("УГОЛЬНИКОВ А.А.", "доцент", "ТиОМП", "TiOMP501"),
                        new EmployeeDTO("ГОЛУБЕВ А.Н.", "ст.преподаватель", "ТиОМП", "TiOMP502"),
                        new EmployeeDTO("КЛИМЕНТЬЕВ А.Л.", "ст.преподаватель", "ТиОМП", "TiOMP503"),
                        new EmployeeDTO("ЛАТУШКИН Д.Г.", "ст.преподаватель", "ТиОМП", "TiOMP504"),
                        new EmployeeDTO("ОКУНЕВ Р.В.", "ст.преподаватель", "ТиОМП", "TiOMP505"),
                        new EmployeeDTO("КОТОВ А.А.", "ассистент ", "ТиОМП", "TiOMP506"),
                        new EmployeeDTO("КУЗЬМЕНКОВ С.М.", "ассистент ", "ТиОМП", "TiOMP507"),
                        new EmployeeDTO("КОТОВИЧ А.В.", "ассистент ", "ТиОМП", "TiOMP508"),
                        new EmployeeDTO("МАРУЩАК А.С.", "ассистент ", "ТиОМП", "TiOMP509"),
                        new EmployeeDTO("ОЛЬШАНСКИЙ А.И.", "доцент", "ТиОМП", "TiOMP510")));

        List<EmployeeDTO> tTMEmployees = new ArrayList<>(
                Arrays.asList(new EmployeeDTO("РЫКЛИН Д.Б.", "зав. кафедрой ", "ТТМ", "TTM240"),
                        new EmployeeDTO("ГРИШАНОВА С.С.", "доцент", "ТТМ", "TTM511"),
                        new EmployeeDTO("ЛОБАЦКАЯ Е.М.", "доцент", "ТТМ", "TTM512"),
                        new EmployeeDTO("МЕДВЕЦКИЙ С.С.", "доцент", "ТТМ", "TTM513"),
                        new EmployeeDTO("СОКОЛОВ Л.Е.", "доцент", "ТТМ", "TTM514"),
                        new EmployeeDTO("ЧАРКОВСКИЙ А.В.", "доцент", "ТТМ", "TTM515"),
                        new EmployeeDTO("КВЕТКОВСКИЙ Д.И.", "ст.преподаватель", "ТТМ", "TTM516"),
                        new EmployeeDTO("ТИХОНОВА Ж.Е.", "ст.преподаватель", "ТТМ", "TTM517")));

        List<EmployeeDTO> fiTMEmployees = new ArrayList<>(
                Arrays.asList(new EmployeeDTO("БУТКЕВИЧ В.Г.", "зав. кафедрой ", "ФиТМ", "FiTM33"),
                        new EmployeeDTO("МАЧИХО Т.А.", "доцент", "ФиТМ", "FiTM527"),
                        new EmployeeDTO("ФЕДОСЕЕВ Г.Н.", "доцент", "ФиТМ", "FiTM528"),
                        new EmployeeDTO("БАБАЕВ В.С.", "ст.преподаватель", "ФиТМ", "FiTM529"),
                        new EmployeeDTO("ДУБАНЕВИЧ Д.Т.", "ст.преподаватель", "ФиТМ", "FiTM530"),
                        new EmployeeDTO("ЛАППО Н.М.", "ст.преподаватель", "ФиТМ", "FiTM531"),
                        new EmployeeDTO("САКЕВИЧ В.Н.", "профессор ", "ФиТМ", "FiTM532")));

        List<EmployeeDTO> fKiSEmployees = new ArrayList<>(
                Arrays.asList(new EmployeeDTO("МУСАТОВ А.Г.", "зав. кафедрой ", "ФКиС", "FKiS190"),
                        new EmployeeDTO("ГОРДЕЦКИЙ А.А.", "ст.преподаватель", "ФКиС", "FKiS518"),
                        new EmployeeDTO("ГУСАКОВ И.Г.", "ст.преподаватель", "ФКиС", "FKiS519"),
                        new EmployeeDTO("ЛИТУНОВСКАЯ Т.В.", "ст.преподаватель", "ФКиС", "FKiS520"),
                        new EmployeeDTO("РЕБИЗОВА Е.А.", "ст.преподаватель", "ФКиС", "FKiS521"),
                        new EmployeeDTO("СЕМЕНОВА А.И.", "ст.преподаватель", "ФКиС", "FKiS522"),
                        new EmployeeDTO("БАНДАРЕВИЧ Е.В.", "преподаватель ", "ФКиС", "FKiS523"),
                        new EmployeeDTO("ГЛУШАНЕНКО Г.В.", "преподаватель ", "ФКиС", "FKiS524"),
                        new EmployeeDTO("ЖЕВЛАКОВ В.Ю.", "преподаватель ", "ФКиС", "FKiS525"),
                        new EmployeeDTO("МАШКОВ А.Ю.", "преподаватель ", "ФКиС", "FKiS526")));

        List<EmployeeDTO> fiKDEmployees = new ArrayList<>(
                Arrays.asList(new EmployeeDTO("СОВЕТНИКОВА О.П.", "зав. кафедрой ", "ФиКД", "FiKD568"),
                        new EmployeeDTO("ПРОКОФЬЕВА Н.Л.", "доцент", "ФиКД", "FiKD223"),
                        new EmployeeDTO("ДЕМ О.Д.", "доцент", "ФиКД", "FiKD533"),
                        new EmployeeDTO("КВАСНИКОВА В.В.", "доцент", "ФиКД", "FiKD534"),
                        new EmployeeDTO("ПРОКОФЬЕВА Н.Л.", "доцент", "ФиКД", "FiKD535"),
                        new EmployeeDTO("ГЕРАСИМОВА О.О.", "ст.преподаватель", "ФиКД", "FiKD536"),
                        new EmployeeDTO("ДОМБРОВСКАЯ Е.Н.", "ст.преподаватель", "ФиКД", "FiKD537"),
                        new EmployeeDTO("ЖУЧКЕВИЧ О.Н.", "ст.преподаватель", "ФиКД", "FiKD538"),
                        new EmployeeDTO("ЛЕВШИЦКАЯ О.Р.", "ст.преподаватель", "ФиКД", "FiKD539"),
                        new EmployeeDTO("МАЦКЕВИЧ Н.В.", "ст.преподаватель", "ФиКД", "FiKD540")));

        List<EmployeeDTO> eiHTEmployees = new ArrayList<>(
                Arrays.asList(new EmployeeDTO("ЯСИНСКАЯ Н.Н.", "зав. кафедрой ", "ЭиХТ", "EiHT312"),
                        new EmployeeDTO("ГРЕЧАНИКОВ А.В.", "доцент", "ЭиХТ", "EiHT541"),
                        new EmployeeDTO("ПОТОЦКИЙ В.Н.", "доцент", "ЭиХТ", "EiHT542"),
                        new EmployeeDTO("СКОБОВА Н.В.", "доцент", "ЭиХТ", "EiHT543"),
                        new EmployeeDTO("ТИМОНОВ И.А.", "доцент", "ЭиХТ", "EiHT544"),
                        new EmployeeDTO("СЕРГЕЕВ В.Ю.", "ст.преподаватель", "ЭиХТ", "EiHT545"),
                        new EmployeeDTO("САВЕНОК В.Е.", "доцент", "ЭиХТ", "EiHT546")

                ));

        List<EmployeeDTO> eEmployees = new ArrayList<>(
                Arrays.asList(new EmployeeDTO("КАСАЕВА Т.В.", "зав. кафедрой ", "Э", "E114"),
                        new EmployeeDTO("БУГАЕВ А.В.", "профессор ", "Э", "E553"),
                        new EmployeeDTO("КАХРО А.А.", "доцент", "Э", "E554"),
                        new EmployeeDTO("ПАКШИНА Т.П.", "доцент", "Э", "E555"),
                        new EmployeeDTO("СОЛОДКИЙ Д.Т.", "доцент", "Э", "E556"),
                        new EmployeeDTO("АНДРИЯНОВА О.М.", "ст.преподаватель", "Э", "E557"),
                        new EmployeeDTO("БЫКОВ К.Р.", "ст.преподаватель", "Э", "E558"),
                        new EmployeeDTO("ЕРМАЧЕНКО О.В.", "ст.преподаватель", "Э", "E559"),
                        new EmployeeDTO("ЖИГАНОВА Т.В.", "ст.преподаватель", "Э", "E560"),
                        new EmployeeDTO("КОВАЛЕНКО Ж.А.", "ст.преподаватель", "Э", "E561"),
                        new EmployeeDTO("ЧЕБОТАРЁВА О.Г.", "ст.преподаватель", "Э", "E562"),
                        new EmployeeDTO("КРАЕНКОВА К.И.", "ст.преподаватель", "Э", "E563"),
                        new EmployeeDTO("ПРУДНИКОВА Л.В.", "ст.преподаватель", "Э", "E564"),
                        new EmployeeDTO("ГУТОРОВА Е.В.", "ассистент ", "Э", "E565"),
                        new EmployeeDTO("ГРУЗНЕВИЧ Е.С.", "ст.преподаватель", "Э", "E566")));

        List<EmployeeDTO> eTiMEmployees = new ArrayList<>(
                Arrays.asList(new EmployeeDTO("ЯШЕВА Г.А.", "зав. кафедрой ", "ЭТиМ", "ETiM313"),
                        new EmployeeDTO("ЕГОРОВА В.К.", "доцент", "ЭТиМ", "ETiM547"),
                        new EmployeeDTO("ГРИГОРЬЕВА С.П.", "ст.преподаватель", "ЭТиМ", "ETiM548"),
                        new EmployeeDTO("НИКОЛАЕВА Ю.Н.", "ст.преподаватель", "ЭТиМ", "ETiM549"),
                        new EmployeeDTO("РУДНИЦКИЙ Д.Б.", "ст.преподаватель", "ЭТиМ", "ETiM550"),
                        new EmployeeDTO("ЧЕРНЫЙ В.П.", "ст.преподаватель", "ЭТиМ", "ETiM551"),
                        new EmployeeDTO("ШЕРСТНЕВА О.М.", "ст.преподаватель", "ЭТиМ", "ETiM552")));

        employees.put("DiM", diMEmployees);
        employees.put("InYaz", inYazEmployees);
        employees.put("ISAP", isapEmployees);
        employees.put("KiTOiO", kiTOiOEmployees);
        employees.put("MiIT", miitEmployees);
        employees.put("MALP", malpEmployees);
        employees.put("M", mEmployees);
        employees.put("SozGum", sozGumEmployees);
        employees.put("TRiT", tritEmployees);
        employees.put("TiOMP", tiompEmployees);
        employees.put("TTM", tTMEmployees);
        employees.put("FiTM", fiTMEmployees);
        employees.put("FKiS", fKiSEmployees);
        employees.put("FiKD", fiKDEmployees);
        employees.put("EiHT", eiHTEmployees);
        employees.put("E", eEmployees);
        employees.put("ETiM", eTiMEmployees);
    }

    public List<EmployeeDTO> getEmployeeByHeadTabel(String tabel) {
        if (tabel.startsWith("rector")) {
            return employees.values().stream().flatMap(List::stream).collect(Collectors.toList());
        } else {
            String department = tabel.replaceAll("\\d", "");
            return (CollectionUtils.isEmpty(employees.get(department))) ? new ArrayList<>() : employees.get(department);
        }
    }

}

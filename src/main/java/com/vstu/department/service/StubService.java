package com.vstu.department.service;

import com.vstu.department.dto.EmployeeDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StubService {

    private static final Map<String, List<EmployeeDTO>> employees;

    static {
        employees = new HashMap<>();
        List<EmployeeDTO> diMEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("АБРАМОВИЧ Н.А.", "доц.", "ДиМ", "DiM1"),
                new EmployeeDTO("АБРАМОВИЧ Н.А.", "зав. каф.", "ДиМ", "DiM2"),
                new EmployeeDTO("БРАНШТЕТЕР П.Л.", "асс.", "ДиМ", "DiM25"),
                new EmployeeDTO("ВАСИЛЬЕВ В.В.", "доц.", "ДиМ", "DiM40"),
                new EmployeeDTO("ВАСИЛЬЕВА Г.С.", "доц.", "ДиМ", "DiM41"),
                new EmployeeDTO("ГУДЧЕНКО О.Ф.", "доц. без ст.", "ДиМ", "DiM61"),
                new EmployeeDTO("ГУРКО И.С.", "ст. преп.", "ДиМ", "DiM62"),
                new EmployeeDTO("КАЗАРНОВСКАЯ Г.В.", "проф.", "ДиМ", "DiM109"),
                new EmployeeDTO("КИРИЛЛОВА И.Л.", "доц. без ст.", "ДиМ", "DiM122"),
                new EmployeeDTO("КОТОВИЧ Т.В.", "проф.", "ДиМ", "DiM143"),
                new EmployeeDTO("КРУПСКАЯ С.Н.", "доц. без ст.", "ДиМ", "DiM146"),
                new EmployeeDTO("КУКУРУЗА С.В.", "ст. преп.", "ДиМ", "DiM151"),
                new EmployeeDTO("ЛИСОВСКАЯ Н.С.", "доц.", "ДиМ", "DiM164"),
                new EmployeeDTO("ЛУЦЕЙКОВИЧ Т.Н.", "доц. без ст.", "ДиМ", "DiM168"),
                new EmployeeDTO("МАКЛЕЦОВА Т.И.", "доц. без ст.", "ДиМ", "DiM170"),
                new EmployeeDTO("МАЛИН А.Г.", "доц.", "ДиМ", "DiM174"),
                new EmployeeDTO("НАГОВИЦЫНА Т.В.", "доц.", "ДиМ", "DiM193"),
                new EmployeeDTO("НЕКРАСОВА В.А.", "ст. преп.", "ДиМ", "DiM197"),
                new EmployeeDTO("ОКСИНЬ С.А.", "доц.", "ДиМ", "DiM204"),
                new EmployeeDTO("ОНУФРИЕНКО С.Г.", "ст. преп.", "ДиМ", "DiM208"),
                new EmployeeDTO("ПОПКОВСКАЯ Л.В.", "доц.", "ДиМ", "DiM218"),
                new EmployeeDTO("ПОПОВА А.В.", "доц. без ст.", "ДиМ", "DiM219"),
                new EmployeeDTO("САМУТИНА Н.Н.", "доц.", "ДиМ", "DiM248"),
                new EmployeeDTO("ТАРАБУКО Н.И.", "доц.", "ДиМ", "DiM275"),
                new EmployeeDTO("ТОЛОБОВА Е.О.", "доц. без ст.", "ДиМ", "DiM278"),
                new EmployeeDTO("УШКИНА И.М.", "ст. преп.", "ДиМ", "DiM284"),
                new EmployeeDTO("ФАЛЕЙ А.В.", "доц. без ст.", "ДиМ", "DiM285"),
                new EmployeeDTO("ФЕДОРЕЦ В.П.", "ст. преп.", "ДиМ", "DiM287")
        ));

        List<EmployeeDTO> inYazEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("БУРДЫКО О.В.", "преп.", "ИнЯз", "InYaz30"),
                new EmployeeDTO("БУРДЫКО О.В.", "преп.", "ИнЯз", "InYaz31"),
                new EmployeeDTO("ИЗМАЙЛОВИЧ О.В.", "ст. преп.", "ИнЯз", "InYaz101"),
                new EmployeeDTO("ИМПЕРОВИЧ В.В.", "ст. преп.", "ИнЯз", "InYaz104"),
                new EmployeeDTO("КОЛДУНЕНКО И.В.", "преп.", "ИнЯз", "InYaz138"),
                new EmployeeDTO("МАШЕРО А.В.", "ст. преп.", "ИнЯз", "InYaz180"),
                new EmployeeDTO("ПАСЮТИНА Ю.Н.", "ст. преп. со ст.", "ИнЯз", "InYaz213"),
                new EmployeeDTO("ПИОТУХ А.А.", "ст. преп.", "ИнЯз", "InYaz217"),
                new EmployeeDTO("СЕРЕБРЯКОВА В.В.", "преп.", "ИнЯз", "InYaz254"),
                new EmployeeDTO("СТЕПАНОВ Д.А.", "зав. каф.", "ИнЯз", "InYaz270"),
                new EmployeeDTO("ЦВИРКО А.В.", "ст. преп.", "ИнЯз", "InYaz292")
        ));

        List<EmployeeDTO> isapEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("БИЗЮК А.Н.", "ст. преп.", "ИСАП", "ISAP19"),
                new EmployeeDTO("БИЗЮК А.Н.", "ст. преп.", "ИСАП", "ISAP20"),
                new EmployeeDTO("БУКИН Ю.А.", "ст. преп.", "ИСАП", "ISAP28"),
                new EmployeeDTO("ВАРДОМАЦКАЯ Е.Ю.", "ст. преп.", "ИСАП", "ISAP38"),
                new EmployeeDTO("ГНИДЕНКО А.К.", "асс.", "ИСАП", "ISAP47"),
                new EmployeeDTO("ДУНИНА Е.Б.", "доц.", "ИСАП", "ISAP82"),
                new EmployeeDTO("ДЯГИЛЕВ А.С.", "доц.", "ИСАП", "ISAP84"),
                new EmployeeDTO("ЖИЗНЕВСКИЙ В.А.", "доц.", "ИСАП", "ISAP93"),
                new EmployeeDTO("ИЛЬЮЩЕНКО А.В.", "доц.", "ИСАП", "ISAP103"),
                new EmployeeDTO("КАЗАКОВ В.Е.", "зав. каф.", "ИСАП", "ISAP108"),
                new EmployeeDTO("КЛИМЕНКОВА С.А.", "ст. преп.", "ИСАП", "ISAP125"),
                new EmployeeDTO("КОВАЛЁВ К.А.", "асс.", "ИСАП", "ISAP128"),
                new EmployeeDTO("КОРНИЕНКО А.А.", "проф.", "ИСАП", "ISAP139"),
                new EmployeeDTO("КУЗНЕЦОВ А.А.", "проф.", "ИСАП", "ISAP147"),
                new EmployeeDTO("КУКСЕВИЧ В.Ф.", "ст. преп.", "ИСАП", "ISAP149"),
                new EmployeeDTO("ЛАНИН С.С.", "асс.", "ИСАП", "ISAP154"),
                new EmployeeDTO("ЛЕОНОВ В.В.", "ст. преп.", "ИСАП", "ISAP161"),
                new EmployeeDTO("МОЗЖАРОВ С.Е.", "ст. преп.", "ИСАП", "ISAP188"),
                new EmployeeDTO("МУРЫЧЕВА В.В.", "доц.", "ИСАП", "ISAP189"),
                new EmployeeDTO("НАУМЕНКО А.М.", "доц.", "ИСАП", "ISAP195"),
                new EmployeeDTO("НОВИКОВ Ю.В.", "доц.", "ИСАП", "ISAP201"),
                new EmployeeDTO("РИНЕЙСКИЙ К.Н.", "ст. преп.", "ИСАП", "ISAP232"),
                new EmployeeDTO("СОКОЛОВА А.С.", "асс.", "ИСАП", "ISAP264"),
                new EmployeeDTO("ЧЕРНЕНКО Д.В.", "ст. преп.", "ИСАП", "ISAP296"),
                new EmployeeDTO("ЧЕРНОВ Е.А.", "асс.", "ИСАП", "ISAP299"),
                new EmployeeDTO("ШУТ В.Н.", "проф.", "ИСАП", "ISAP310")
        ));


        List<EmployeeDTO> kiTOiOEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("АЛАХОВА С.С.", "ст. преп.", "КиТОиО", "KiTOiO4"),
                new EmployeeDTO("БОДЯЛО Н.Н.", "зав. каф.", "КиТОиО", "KiTOiO21"),
                new EmployeeDTO("БОНДАРЕВА Е.В.", "ст. преп.", "КиТОиО", "KiTOiO22"),
                new EmployeeDTO("БОРИСОВА Т.М.", "доц.", "КиТОиО", "KiTOiO23"),
                new EmployeeDTO("ГОРБАЧИК В.Е.", "проф.", "КиТОиО", "KiTOiO51"),
                new EmployeeDTO("ДОВЫДЕНКОВА В.П.", "ст. преп. со ст.", "КиТОиО", "KiTOiO76"),
                new EmployeeDTO("ЗИМИНА Е.Л.", "доц.", "КиТОиО", "KiTOiO99"),
                new EmployeeDTO("ИВАНОВА Н.Н.", "ст. преп.", "КиТОиО", "KiTOiO100"),
                new EmployeeDTO("КУКУШКИНА Ю.М.", "ст. преп.", "КиТОиО", "KiTOiO153"),
                new EmployeeDTO("МИЛЮШКОВА Ю.В.", "доц.", "КиТОиО", "KiTOiO186"),
                new EmployeeDTO("ПАНКЕВИЧ Д.К.", "доц.", "КиТОиО", "KiTOiO212"),
                new EmployeeDTO("ПЕТРОВА Р.С.", "асс.", "КиТОиО", "KiTOiO215"),
                new EmployeeDTO("ТОМАШЕВА Р.Н.", "доц.", "КиТОиО", "KiTOiO279"),
                new EmployeeDTO("УЛЬЯНОВА Н.В.", "доц.", "КиТОиО", "KiTOiO281"),
                new EmployeeDTO("ФУРАШОВА С.Л.", "доц.", "КиТОиО", "KiTOiO290"),
                new EmployeeDTO("ЧОНГАРСКАЯ Л.М.", "доц.", "КиТОиО", "KiTOiO302")
        ));

        List<EmployeeDTO> miitEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("ВАРДОМАЦКАЯ Е.Ю.", "ст. преп.", "МиИТ", "MiIT39"),
                new EmployeeDTO("ГРИШАЕВ А.Н.", "ст. преп.", "МиИТ", "MiIT57"),
                new EmployeeDTO("ДЕНИСОВ В.С.", "доц.", "МиИТ", "MiIT72"),
                new EmployeeDTO("ДЕРКАЧЕНКО П.Г.", "ст. преп.", "МиИТ", "MiIT73"),
                new EmployeeDTO("ДЖЕЖОРА А.А.", "зав. каф.", "МиИТ", "MiIT74"),
                new EmployeeDTO("ДМИТРИЕВ А.П.", "ст. преп.", "МиИТ", "MiIT75"),
                new EmployeeDTO("ДЯГИЛЕВ А.С.", "доц.", "МиИТ", "MiIT85"),
                new EmployeeDTO("ЗАВАЦКИЙ Ю.А.", "ст. преп.", "МиИТ", "MiIT96"),
                new EmployeeDTO("ЗАГУРСКИЙ В.Н.", "доц.", "МиИТ", "MiIT97"),
                new EmployeeDTO("КОВАЛЕНКО А.В.", "ст. преп.", "МиИТ", "MiIT129"),
                new EmployeeDTO("КОСТИН П.А.", "доц.", "МиИТ", "MiIT141"),
                new EmployeeDTO("ЛУЦЕЙКОВИЧ В.И.", "ст. преп.", "МиИТ", "MiIT167"),
                new EmployeeDTO("МАЛАШЕНКОВ С.И.", "доц.", "МиИТ", "MiIT173"),
                new EmployeeDTO("МАНДРИК О.Г.", "ст. преп.", "МиИТ", "MiIT175"),
                new EmployeeDTO("МЕТЕЛИЦА О.М.", "доц.", "МиИТ", "MiIT184"),
                new EmployeeDTO("НИКОНОВА Т.В.", "доц.", "МиИТ", "MiIT200"),
                new EmployeeDTO("РОЗОВА Л.И.", "доц.", "МиИТ", "MiIT233"),
                new EmployeeDTO("РУБАНИК О.Е.", "ст. преп.", "МиИТ", "MiIT236"),
                new EmployeeDTO("СИЛИВОНЧИК В.В.", "ст. преп.", "МиИТ", "MiIT255"),
                new EmployeeDTO("СТАСЕНЯ Т.П.", "ст. преп.", "МиИТ", "MiIT268"),
                new EmployeeDTO("СТАТКОВСКИЙ Н.С.", "ст. преп.", "МиИТ", "MiIT269")
        ));

        List<EmployeeDTO> malpEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("БЕЛОВ А.А.", "доц.", "МАЛП", "MALP16"),
                new EmployeeDTO("БУЕВИЧ Т.В.", "доц.", "МАЛП", "MALP27"),
                new EmployeeDTO("КИРИЛЛОВ А.Г.", "зав. каф.", "МАЛП", "MALP121"),
                new EmployeeDTO("РАДКЕВИЧ А.В.", "ст. преп.", "МАЛП", "MALP229"),
                new EmployeeDTO("СУНКУЕВ Б.С.", "проф.", "МАЛП", "MALP273")
        ));

        List<EmployeeDTO> mEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("АЛЕКСЕЕВА Е.А.", "ст. преп.", "М", "M6"),
                new EmployeeDTO("БАБЕНЯ И.Г.", "ст. преп.", "М", "M11"),
                new EmployeeDTO("ВАНКЕВИЧ Е.В.", "проф.", "М", "M36"),
                new EmployeeDTO("ГОНЧАРОВА Е.С.", "ст. преп.", "М", "M50"),
                new EmployeeDTO("ДАНИЛЕВИЧ Т.А.", "ст. преп.", "М", "M66"),
                new EmployeeDTO("ЗАЙЦЕВА О.В.", "ст. преп.", "М", "M98"),
                new EmployeeDTO("КАЛИНОВСКАЯ И.Н.", "доц.", "М", "M110"),
                new EmployeeDTO("КОРОБОВА Е.Н.", "доц.", "М", "M140"),
                new EmployeeDTO("САВИЦКАЯ Т.Б.", "зав. каф.", "М", "M243"),
                new EmployeeDTO("СЕДОВА М.П.", "асс.", "М", "M249"),
                new EmployeeDTO("СКВОРЦОВ В.А.", "доц.", "М", "M256"),
                new EmployeeDTO("СУВОРОВ А.П.", "доц.", "М", "M272"),
                new EmployeeDTO("СЫСОЕВ И.П.", "доц.", "М", "M274"),
                new EmployeeDTO("ЧЕРНЕНКОВА А.А.", "асс.", "М", "M298")
        ));

        List<EmployeeDTO> sozGumEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("ИСАЧЕНКО А.В.", "ст. преп.", "СоцГум", "SozGum106"),
                new EmployeeDTO("ЛЕБЕДЕВА С.В.", "ст. преп.", "СоцГум", "SozGum159"),
                new EmployeeDTO("ЛУЧЕНКОВА Е.С.", "зав. каф.", "СоцГум", "SozGum169"),
                new EmployeeDTO("МЯДЕЛЬ А.П.", "доц.", "СоцГум", "SozGum192"),
                new EmployeeDTO("ОСТАПИШИНА Л.О.", "ст. преп.", "СоцГум", "SozGum209"),
                new EmployeeDTO("ПУСТЯК М.И.", "асс.", "СоцГум", "SozGum226"),
                new EmployeeDTO("РОСТОВСКАЯ О.М.", "асс.", "СоцГум", "SozGum234"),
                new EmployeeDTO("РУДКО Е.А.", "доц.", "СоцГум", "SozGum237"),
                new EmployeeDTO("СУББОТИН А.А.", "ст. преп.", "СоцГум", "SozGum271"),
                new EmployeeDTO("УТКЕВИЧ О.И.", "доц.", "СоцГум", "SozGum282"),
                new EmployeeDTO("ХАДАНЕНАК В.М.", "доц.", "СоцГум", "SozGum291"),
                new EmployeeDTO("ЧЕСНОКОВА О.И.", "доц.", "СоцГум", "SozGum301")
        ));

        List<EmployeeDTO> tritEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("БЕЛИКОВ С.А.", "ст. преп.", "ТРиТ", "TRiT15"),
                new EmployeeDTO("БОРОЗНА В.Д.", "асс.", "ТРиТ", "TRiT24"),
                new EmployeeDTO("БУЛАНЧИКОВ И.А.", "ст. преп.", "ТРиТ", "TRiT29"),
                new EmployeeDTO("БУРКИН А.Н.", "зав. каф.", "ТРиТ", "TRiT32"),
                new EmployeeDTO("ГЕРАСИМОВИЧ Е.М.", "асс.", "ТРиТ", "TRiT43"),
                new EmployeeDTO("ГРОШЕВ И.М.", "доц.", "ТРиТ", "TRiT59"),
                new EmployeeDTO("ДОЛГАН М.И.", "асс.", "ТРиТ", "TRiT77"),
                new EmployeeDTO("КАРПУШЕНКО И.С.", "ст. преп.", "ТРиТ", "TRiT112"),
                new EmployeeDTO("КОВАЛЬЧУК Е.А.", "доц.", "ТРиТ", "TRiT133"),
                new EmployeeDTO("КОЗЛОВСКАЯ Л.Г.", "ст. преп.", "ТРиТ", "TRiT137"),
                new EmployeeDTO("МАХОНЬ А.Н.", "доц.", "ТРиТ", "TRiT177"),
                new EmployeeDTO("НАУМЕНКО А.А.", "доц.", "ТРиТ", "TRiT194"),
                new EmployeeDTO("ПЕТЮЛЬ И.А.", "доц.", "ТРиТ", "TRiT216"),
                new EmployeeDTO("ШЕВЦОВА М.В.", "доц.", "ТРиТ", "TRiT305"),
                new EmployeeDTO("ШЕРЕМЕТ Е.А.", "доц.", "ТРиТ", "TRiT306")
        ));

        List<EmployeeDTO> tiompEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("АЛЕКСЕЕВ И.С.", "доц.", "ТиОМП", "TiOMP5"),
                new EmployeeDTO("БЕЛОВ Е.В.", "доц.", "ТиОМП", "TiOMP17"),
                new EmployeeDTO("БЕЛЯКОВ Н.В.", "доц.", "ТиОМП", "TiOMP18"),
                new EmployeeDTO("ГОЛУБЕВ А.Н.", "ст. преп.", "ТиОМП", "TiOMP49"),
                new EmployeeDTO("ГУСАРОВ А.М.", "доц.", "ТиОМП", "TiOMP65"),
                new EmployeeDTO("ДРЮКОВ В.В.", "доц.", "ТиОМП", "TiOMP80"),
                new EmployeeDTO("ЖЕРНОСЕК С.В.", "доц.", "ТиОМП", "TiOMP91"),
                new EmployeeDTO("КЛИМЕНКОВ С.С.", "проф.", "ТиОМП", "TiOMP124"),
                new EmployeeDTO("КЛИМЕНТЬЕВ А.Л.", "ст. преп.", "ТиОМП", "TiOMP127"),
                new EmployeeDTO("КОВЧУР А.С.", "доц.", "ТиОМП", "TiOMP134"),
                new EmployeeDTO("КОТОВ А.А.", "асс.", "ТиОМП", "TiOMP142"),
                new EmployeeDTO("КУЗЬМЕНКОВ С.М.", "асс.", "ТиОМП", "TiOMP148"),
                new EmployeeDTO("ЛАТУШКИН Д.Г.", "ст. преп.", "ТиОМП", "TiOMP157"),
                new EmployeeDTO("МАХАРИНСКИЙ Ю.Е.", "доц. без ст.", "ТиОМП", "TiOMP176"),
                new EmployeeDTO("ОКУНЕВ Р.В.", "асс.", "ТиОМП", "TiOMP205"),
                new EmployeeDTO("ОЛЬШАНСКИЙ В.И.", "зав. каф.", "ТиОМП", "TiOMP207"),
                new EmployeeDTO("ПУТЕЕВ Н.В.", "доц.", "ТиОМП", "TiOMP227"),
                new EmployeeDTO("ПЯТОВ В.В.", "проф.", "ТиОМП", "TiOMP228"),
                new EmployeeDTO("САВИЦКИЙ В.В.", "доц.", "ТиОМП", "TiOMP244"),
                new EmployeeDTO("УГОЛЬНИКОВ А.А.", "доц.", "ТиОМП", "TiOMP280")
        ));

        List<EmployeeDTO> tTMEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("АКИНДИНОВА Н.С.", "доц.", "ТТМ", "TTM3"),
                new EmployeeDTO("БАШМЕТОВ В.С.", "проф.", "ТТМ", "TTM14"),
                new EmployeeDTO("ГРИШАНОВА С.С.", "доц.", "ТТМ", "TTM58"),
                new EmployeeDTO("КВЕТКОВСКИЙ Д.И.", "ст. преп.", "ТТМ", "TTM120"),
                new EmployeeDTO("КОГАН А.Г.", "проф.", "ТТМ", "TTM136"),
                new EmployeeDTO("КУКУШКИН М.Л.", "доц.", "ТТМ", "TTM152"),
                new EmployeeDTO("ЛОБАЦКАЯ Е.М.", "доц.", "ТТМ", "TTM165"),
                new EmployeeDTO("МЕДВЕЦКИЙ С.С.", "доц.", "ТТМ", "TTM183"),
                new EmployeeDTO("РАССОХИНА И.М.", "доц.", "ТТМ", "TTM230"),
                new EmployeeDTO("РЫКЛИН Д.Б.", "зав. каф.", "ТТМ", "TTM240"),
                new EmployeeDTO("СКОБОВА Н.В.", "доц.", "ТТМ", "TTM258"),
                new EmployeeDTO("СОКОЛОВ Л.Е.", "доц.", "ТТМ", "TTM263"),
                new EmployeeDTO("ТИХОНОВА Ж.Е.", "ст. преп.", "ТТМ", "TTM277"),
                new EmployeeDTO("ЧАРКОВСКИЙ А.В.", "доц.", "ТТМ", "TTM295")
        ));

        List<EmployeeDTO> fiTMEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("АРИСТОВ А.А.", "асс.", "ФиТМ", "FiTM9"),
                new EmployeeDTO("БАБАЕВ В.С.", "ст. преп.", "ФиТМ", "FiTM10"),
                new EmployeeDTO("БУТКЕВИЧ В.Г.", "зав. каф.", "ФиТМ", "FiTM33"),
                new EmployeeDTO("ДЕДЮРО В.В.", "асс.", "ФиТМ", "FiTM68"),
                new EmployeeDTO("КРАСНЕР С.Ю.", "доц.", "ФиТМ", "FiTM145"),
                new EmployeeDTO("ЛАППО Н.М.", "ст. преп.", "ФиТМ", "FiTM155"),
                new EmployeeDTO("ЛОКТИОНОВ А.В.", "проф.", "ФиТМ", "FiTM166"),
                new EmployeeDTO("МАЧИХО Т.А.", "доц.", "ФиТМ", "FiTM179"),
                new EmployeeDTO("МИЛЮКИНА С.Н.", "доц.", "ФиТМ", "FiTM185"),
                new EmployeeDTO("РУБАНИК В.В.", "проф.", "ФиТМ", "FiTM235"),
                new EmployeeDTO("САКЕВИЧ В.Н.", "проф.", "ФиТМ", "FiTM246"),
                new EmployeeDTO("ФЕДОСЕЕВ Г.Н.", "доц.", "ФиТМ", "FiTM288"),
                new EmployeeDTO("ШИЛИН А.Д.", "доц.", "ФиТМ", "FiTM309")
        ));

        List<EmployeeDTO> fKiSEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("ГИЛЬ Г.В.", "преп.", "ФКиС", "FKiS44"),
                new EmployeeDTO("ГЛУШАНЕНКО Г.В.", "преп.", "ФКиС", "FKiS46"),
                new EmployeeDTO("ГОЛОВЕШКО О.М.", "ст. преп.", "ФКиС", "FKiS48"),
                new EmployeeDTO("ГОРДЕЦКИЙ А.А.", "ст. преп.", "ФКиС", "FKiS52"),
                new EmployeeDTO("ГУРОВ И.Б.", "ст. преп.", "ФКиС", "FKiS63"),
                new EmployeeDTO("ГУСАКОВ И.Г.", "ст. преп.", "ФКиС", "FKiS64"),
                new EmployeeDTO("ДЕДКОВ В.Л.", "преп.", "ФКиС", "FKiS67"),
                new EmployeeDTO("ЕКОМАСОВА Т.В.", "ст. преп.", "ФКиС", "FKiS87"),
                new EmployeeDTO("ЕСИПОВА В.А.", "преп.", "ФКиС", "FKiS90"),
                new EmployeeDTO("КАЧАН А.В.", "преп.", "ФКиС", "FKiS116"),
                new EmployeeDTO("МАШКОВ А.Ю.", "преп.", "ФКиС", "FKiS181"),
                new EmployeeDTO("МУСАТОВ А.Г.", "зав. каф.", "ФКиС", "FKiS190"),
                new EmployeeDTO("НОВИЦКИЙ П.И.", "доц.", "ФКиС", "FKiS203"),
                new EmployeeDTO("ПЕТРАШКО В.А.", "ст. преп.", "ФКиС", "FKiS214"),
                new EmployeeDTO("РЕБИЗОВА Е.А.", "ст. преп.", "ФКиС", "FKiS231"),
                new EmployeeDTO("СЕМЕНОВА А.И.", "преп.", "ФКиС", "FKiS250"),
                new EmployeeDTO("СОБОЛЕВ С.М.", "ст. преп.", "ФКиС", "FKiS260"),
                new EmployeeDTO("ШАЛАБОДОВА Т.Ю.", "преп.", "ФКиС", "FKiS303"),
                new EmployeeDTO("ЯНЧЕНКО Ю.А.", "ст. преп.", "ФКиС", "FKiS311")
        ));

        List<EmployeeDTO> fiKDEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("ГЕРАСИМОВА О.О.", "ст. преп.", "ФиКД", "FiKD42"),
                new EmployeeDTO("ДЕМ О.Д.", "доц.", "ФиКД", "FiKD69"),
                new EmployeeDTO("ДОМБРОВСКАЯ Е.Н.", "ст. преп.", "ФиКД", "FiKD78"),
                new EmployeeDTO("ЖУЧКЕВИЧ О.Н.", "ст. преп.", "ФиКД", "FiKD94"),
                new EmployeeDTO("КВАСНИКОВА В.В.", "доц.", "ФиКД", "FiKD117"),
                new EmployeeDTO("ЛЕВШИЦКАЯ О.Р.", "ст. преп.", "ФиКД", "FiKD160"),
                new EmployeeDTO("МАЦКЕВИЧ Н.В.", "ст. преп.", "ФиКД", "FiKD178"),
                new EmployeeDTO("ПРОКОФЬЕВА Н.Л.", "зав. каф.", "ФиКД", "FiKD223"),
                new EmployeeDTO("РУЛЁВА А.А.", "асс.", "ФиКД", "FiKD239"),
                new EmployeeDTO("САМАЛЬ С.А.", "проф.", "ФиКД", "FiKD247"),
                new EmployeeDTO("СОВЕТНИКОВА О.П.", "доц.", "ФиКД", "FiKD261")
        ));

        List<EmployeeDTO> eiHTEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("ГРЕЧАНИКОВ А.В.", "доц.", "ЭиХТ", "EiHT54"),
                new EmployeeDTO("КОВЧУР С.Г.", "проф.", "ЭиХТ", "EiHT135"),
                new EmployeeDTO("ПОТОЦКИЙ В.Н.", "доц.", "ЭиХТ", "EiHT221"),
                new EmployeeDTO("САВЕНОК В.Е.", "доц.", "ЭиХТ", "EiHT241"),
                new EmployeeDTO("СЕРГЕЕВ В.Ю.", "ст. преп.", "ЭиХТ", "EiHT253"),
                new EmployeeDTO("СКОБОВА Н.В.", "доц.", "ЭиХТ", "EiHT257"),
                new EmployeeDTO("СОКОЛОВА Т.Н.", "доц.", "ЭиХТ", "EiHT266"),
                new EmployeeDTO("ТИМОНОВ И.А.", "доц.", "ЭиХТ", "EiHT276"),
                new EmployeeDTO("УШАКОВ В.В.", "ст. преп.", "ЭиХТ", "EiHT283"),
                new EmployeeDTO("ЯСИНСКАЯ Н.Н.", "зав. каф.", "ЭиХТ", "EiHT312")

        ));

        List<EmployeeDTO> eEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("АНДРИЯНОВА О.М.", "ст. преп.", "Э", "E7"),
                new EmployeeDTO("БУГАЕВ А.В.", "проф.", "Э", "E26"),
                new EmployeeDTO("БЫКОВ К.Р.", "ст. преп.", "Э", "E34"),
                new EmployeeDTO("ВАНКЕВИЧ Е.В.", "проф.", "Э", "E37"),
                new EmployeeDTO("ГРУЗНЕВИЧ Е.С.", "ст. преп.", "Э", "E60"),
                new EmployeeDTO("ДУЛЕБО Е.Ю.", "ст. преп.", "Э", "E81"),
                new EmployeeDTO("ЕРМАЧЕНКО О.В.", "ст. преп.", "Э", "E89"),
                new EmployeeDTO("ЖИГАНОВА Т.В.", "асс.", "Э", "E92"),
                new EmployeeDTO("КАСАЕВА Т.В.", "зав. каф.", "Э", "E114"),
                new EmployeeDTO("КАХРО А.А.", "доц.", "Э", "E115"),
                new EmployeeDTO("КОВАЛЕНКО Ж.А.", "ст. преп.", "Э", "E130"),
                new EmployeeDTO("КРАЕНКОВА К.И.", "ст. преп.", "Э", "E144"),
                new EmployeeDTO("ЛИПСКИЙ А.В.", "ст. преп.", "Э", "E163"),
                new EmployeeDTO("МАКОВСКАЯ Н.В.", "проф.", "Э", "E172"),
                new EmployeeDTO("ПАКШИНА Т.П.", "доц.", "Э", "E211"),
                new EmployeeDTO("ПРУДНИКОВА Л.В.", "ст. преп.", "Э", "E225"),
                new EmployeeDTO("СОЛОДКИЙ Д.Т.", "доц.", "Э", "E267"),
                new EmployeeDTO("ЦЫНКОВИЧ О.Г.", "ст. преп.", "Э", "E293")
        ));

        List<EmployeeDTO> eTiMEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("ВАЙЛУНОВА Ю.Г.", "доц.", "ЭТиМ", "ETiM35"),
                new EmployeeDTO("ГРИГОРЬЕВА С.П.", "ст. преп.", "ЭТиМ", "ETiM56"),
                new EmployeeDTO("ЕГОРОВА В.К.", "доц.", "ЭТиМ", "ETiM86"),
                new EmployeeDTO("КАЛИНОВСКАЯ И.Н.", "доц.", "ЭТиМ", "ETiM111"),
                new EmployeeDTO("ЛЕБЕДЕВА Е.Н.", "доц.", "ЭТиМ", "ETiM158"),
                new EmployeeDTO("НИКОЛАЕВА Ю.Н.", "ст. преп.", "ЭТиМ", "ETiM198"),
                new EmployeeDTO("ПРОХОРЕНКОВА И.А.", "асс.", "ЭТиМ", "ETiM224"),
                new EmployeeDTO("РУДНИЦКИЙ Д.Б.", "ст. преп.", "ЭТиМ", "ETiM238"),
                new EmployeeDTO("САВОСИНА А.А.", "ст. преп.", "ЭТиМ", "ETiM245"),
                new EmployeeDTO("СЕМЕНЧУКОВА И.Ю.", "доц.", "ЭТиМ", "ETiM251"),
                new EmployeeDTO("СЛОНИМСКАЯ М.А.", "доц.", "ЭТиМ", "ETiM259"),
                new EmployeeDTO("ЧЕРНЫЙ В.П.", "ст. преп.", "ЭТиМ", "ETiM300"),
                new EmployeeDTO("ШЕРСТНЕВА О.М.", "ст. преп.", "ЭТиМ", "ETiM307"),
                new EmployeeDTO("ЯШЕВА Г.А.", "зав. каф.", "ЭТиМ", "ETiM313")
        ));

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

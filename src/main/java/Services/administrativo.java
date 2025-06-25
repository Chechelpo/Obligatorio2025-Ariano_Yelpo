package Services;

import Services.Data.DataOverseer;
import Services.Reports.*;

public class administrativo {
    private final DataOverseer data = new DataOverseer();

    public void executeReport(int reportNumber) {
        long inicio;
        long fin;
        switch (reportNumber) {
            case 1:
                FirstReport.firstReport(data.getPeliculasPorId(),data.getReviews());
                break;
                case 2:
                    SecondReport.secondReport(data.getPeliculasPorId(),data.getReviews());
                    break;
                    case 3:
                        ThirdReport.thirdReport(data.getSagasPorID());
                        break;
            case 4:
                FourthReport.fourthReport(data.getDirectorPorID(),data.getReviews());
                break;
                case 5:
                    FifthReport.fifthReport(data.getActors(),data.getReviews());
                    break;
                    case 6:
                        SixthReport.sixthReport(data.getReviews());
                        case 7:
                            return;
                            default: break;

        }
    }
}

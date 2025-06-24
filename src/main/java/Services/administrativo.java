package Services;

import Services.Data.DataOverseer;
import Services.Reports.FirstReport;
import Services.Reports.FourthReport;
import Services.Reports.SecondReport;
import Services.Reports.ThirdReport;

public class administrativo {
    private final DataOverseer data = new DataOverseer();

    public void executeReport(int reportNumber) {
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
                    break;
                    case 6:
                        break;
                        case 7:
                            return;
                            default: break;

        }
    }
}

package ohtu;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;
    public static final int zero = 0;
    public static final int fifteen = 1;
    public static final int thrity = 2;
    public static final int fourty = 3;
    public static final int advantage = 4;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            m_score1 += 1;
        } else {
            m_score2 += 1;
        }
    }

    public String getScore() {
        String score = "";

        if (m_score1 == m_score2) {
            score = tulosTasan();
        } else if (m_score1 >= advantage || m_score2 >= advantage) {
            score = tulostaEtu();
        } else {
            score += tulostaTilanne();
        }
        return score;
    }

    public String tulosTasan() {
        String score;
        switch (m_score1) {
            case zero:
                score = "Love-All";
                break;
            case fifteen:
                score = "Fifteen-All";
                break;
            case thrity:
                score = "Thirty-All";
                break;
            case fourty:
                score = "Forty-All";
                break;
            default:
                score = "Deuce";
                break;

        }
        return score;
    }

    public String tulostaEtu() {
        String score;
        int diffInScore = m_score1 - m_score2;
        if (diffInScore == 1) {
            score = "Advantage player1";
        } else if (diffInScore == -1) {
            score = "Advantage player2";
        } else if (diffInScore > 1) {
            score = "Win for player1";
        } else {
            score = "Win for player2";
        }

        return score;
    }

    public String tulostaTilanne() {
        String score = "";

        score += muutaPisteTeksitksi(m_score1);
        score += "-";
        score += muutaPisteTeksitksi(m_score2);

        return score;
    }

    public String muutaPisteTeksitksi(int tempScore) {
        String score = "";
        switch (tempScore) {
            case zero:
                score += "Love";
                break;
            case fifteen:
                score += "Fifteen";
                break;
            case thrity:
                score += "Thirty";
                break;
            case fourty:
                score += "Forty";
                break;
        }
        return score;
    }

}

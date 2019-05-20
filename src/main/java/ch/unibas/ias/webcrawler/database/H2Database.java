package ch.unibas.ias.webcrawler.database;

import ch.unibas.ias.webcrawler.webcrawler.WordPressLoginSecurityStats;

import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class H2Database implements Database {

  private PreparedStatement insertStmt;

  public H2Database(DBConnection conn) {
    try {
      insertStmt = conn.newStatement("INSERT INTO page (url, html, header, date, stat_wp_admin, stat_strong_password, stat_humanity, stat_jetpack, stat_recaptcha) VALUES (?,?,?,?,?,?,?,?,?)");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void addRecord(String url, String html, String httpHeaders, Date downloadDate, WordPressLoginSecurityStats stats) {
    try {
      insertStmt.setString(1, url);
      insertStmt.setCharacterStream(2, new StringReader(html));
      insertStmt.setString(3, httpHeaders);
      insertStmt.setTimestamp(4, new Timestamp(downloadDate.getTime()));
      setStats(insertStmt, stats);
      insertStmt.execute();
    } catch (SQLException e) {
      System.out.println("Warning: " + e.getMessage());
    }
  }

  public void setStats(PreparedStatement s, WordPressLoginSecurityStats stats) throws SQLException {
    if (stats == null) {
      s.setBoolean(5, false);
      s.setBoolean(6, false);
      s.setBoolean(7, false);
      s.setBoolean(8, false);
      s.setBoolean(9, false);
    } else {
      s.setBoolean(5, stats.getCanAccessAdminLogin());
      s.setBoolean(6, stats.getHasForceStrongPassword());
      s.setBoolean(7, stats.getHasProveYourHumanity());
      s.setBoolean(8, stats.getHasJetpackPlugin());
      s.setBoolean(9, stats.getHasRecaptcha());
    }
  }
}

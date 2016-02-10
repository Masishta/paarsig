package masishta.parsig;

/**
 * Created by Masishta on 1/27/2016.
 */
public class ParsigInfoHolder
{
  private String pes;
  private String desc;
  private String en;
  private String de;
  private String fr;
  private String refs;

  /* ParsigInfoHolder Constructor */
  public ParsigInfoHolder (String persian, String description, String english,
                           String deutsch, String french, String reference)
  {
    pes  = persian;
    desc = description;
    en   = english;
    de   = deutsch;
    fr   = french;
    refs = reference;
  }

  public String getPes ()
  {
    return pes;
  }

  public void setPes (String pes)
  {
    this.pes = pes;
  }

  public String getDesc ()
  {
    return desc;
  }

  public void setDesc (String desc)
  {
    this.desc = desc;
  }

  public String getEn ()
  {
    return en;
  }

  public void setEn (String en)
  {
    this.en = en;
  }

  public String getDe ()
  {
    return de;
  }

  public void setDe (String de)
  {
    this.de = de;
  }

  public String getFr ()
  {
    return fr;
  }

  public void setFr (String fr)
  {
    this.fr = fr;
  }

  public String getRefs ()
  {
    return refs;
  }

  public void setRefs (String refs)
  {
    this.refs = refs;
  }
}

package masishta.parsig;

/**
 * Created by Masishta on 10/19/2015.
 */
public class Info
{
    int _id;
    String _name;
    String _description;


    // Empty Constructor
    public Info()
    {

    }

    // Constructor
    public Info(int id, String name, String description)
    {
        this._id = id;
        this._name = name;
        this._description = description;
    }

    // Constructor
    public Info(String name, String description)
    {
        this._name = name;
        this._description = description;
    }

    // Getting ID
    public int getID()
    {
        return this._id;
    }

    // Setting ID
    public  void setID(int id)
    {
        this._id = id;
    }

    // Getting Name
    public String getName()
    {
        return this._name;
    }

    // Setting Name
    public void setName(String name)
    {
        this._name = name;
    }

    // Getting Description
    public String getDescription()
    {
        return this._description;
    }

    // Setting Description
    public void setDescription(String description)
    {
        this._description = description;
    }

}
package masishta.parsig;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.AsyncTask;


public class MainActivity extends AppCompatActivity
{
  private ProgressDialog pdialog;
  private static final String url = "http://api.androidhive.info/contancts";
  private static final String TAG_CONTACTS = "contacts";
  private static final String TAG_NAME = "name";
  private static final String TAG_MOBILE = "mobile";

  JSONArray infos = null;
  ArrayList<HashMap<String, String>> infoList;

  @Override
  protected void onCreate (Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
    setSupportActionBar (toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById (R.id.fab);
    fab.setOnClickListener (new View.OnClickListener ()
    {
      @Override
      public void onClick (View view)
      {
        Snackbar.make (view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction ("Action", null).show ();
      }
    });
  }

  private class GetInfo extends AsyncTask<Void, Void, Void>
  {
    @Override
    protected void onPreExecute ()
    {
      super.onPreExecute ();
      pdialog = new ProgressDialog (MainActivity.this);
      pdialog.setMessage ("Please Wait...");
      pdialog.setCancelable (false);
      pdialog.show ();
    }

    @Override
    protected Void doInBackground (Void... arg0)
    {
      ServiceHandlerold sh = new ServiceHandlerold ();
      String jsonString = sh.makeServiceCall (url, ServiceHandlerold.GET);

      Log.d ("Response: ", "> " + jsonString);

      if (jsonString != null)
        {
          try
            {
              JSONObject jsonObject = new JSONObject (jsonString);

              infos = jsonObject.getJSONArray (TAG_CONTACTS);

              for (int i = 0; i < infos.length (); i++)
                {
                  JSONObject c = infos.getJSONObject (i);

                  String name = c.getString (TAG_NAME);
                  String mobile = c.getString (TAG_MOBILE);

                  HashMap<String, String> info = new HashMap<String, String> ();
                  info.put (TAG_NAME, name);
                  info.put (TAG_MOBILE, mobile);

                  infoList.add (info);
                }
            }
          catch (JSONException e)
            {
              e.printStackTrace ();
            }
        }
      else
        {
          Log.e ("Service Handler", "Could not get any data from url");
        }
      return null;
    }

    @Override
    protected void onPostExecute (Void result)
    {
      super.onPreExecute ();
      if (pdialog.isShowing ())
        pdialog.dismiss ();

  /*          ListAdapter adapter = new SimpleAdapter(
                    SingleInfoActivity.class,
                    infoList,
                    R.layout.list_item,
                    new String[]{TAG_NAME, TAG_MOBILE},
                    new int[]{R.id.name, R.id.mobile});
            setListAdapter(adapter); */
    }
  }


  @Override
  public boolean onCreateOptionsMenu (Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater ().inflate (R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected (MenuItem item)
  {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId ();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings)
      {
        return true;
      }

    return super.onOptionsItemSelected (item);
  }
}

package masishta.parsig;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
{
  ArrayList<HashMap<String, String>> infoList;
  private EditText edittext;
  private TextView errorView;
  Button button;
  String tag = "Main";
  String searchWord = "";

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

    edittext = (EditText) findViewById(R.id.editText);
    searchFilterListener ();
    errorView = (TextView) findViewById(R.id.errorView);
    button = (Button) findViewById(R.id.button);
    button.setOnClickListener(new buttonClickListener());
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

  String getKeyword(EditText key)
  {
    Log.d(tag, "getKeyword");
    String keyword = key.getText().toString();
    if (!checkString(keyword))
      return keyword;
    return "error";
  }

  Boolean checkString(String key)
  {
    Log.d(tag, "checkString");
    Pattern pattern = Pattern.compile("[~#@$*+%&{}<>\\[\\]|\"\'/;.,:()!?؟«»_^]");
    Matcher matcher = pattern.matcher(key);
    return matcher.find();
  }

  private class buttonClickListener implements View.OnClickListener
  {
    @Override
    public void onClick(View v)
    {
      Log.d(tag,"onClick");
      String key = getKeyword(edittext);
      Toast.makeText (MainActivity.this, key, Toast.LENGTH_SHORT).show();
    }
  }

  public void searchFilterListener()
  {
    edittext.addTextChangedListener(new TextWatcher ()
    {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after)
      {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count)
      {
        Log.d(tag, "onTextChanged");
        if(checkString(s.toString()))
          {
            errorView.setText("Your search contains illegal character");
            button.setEnabled(false);
          }
        else
          {
            errorView.setText("");
            button.setEnabled(true);
          }
      }

      @Override
      public void afterTextChanged(Editable s)
      {

      }
    });
  }
}

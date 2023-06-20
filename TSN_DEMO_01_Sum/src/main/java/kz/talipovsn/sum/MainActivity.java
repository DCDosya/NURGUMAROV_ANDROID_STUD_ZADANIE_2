package kz.talipovsn.sum;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextWatcher;
import android.text.Editable;

public class MainActivity extends AppCompatActivity {

    EditText editText_a; // Переменная для доступа к компоненту со значением "a"
    EditText editText_b; // Переменная для доступа к компоненту со значением "b"
    EditText editText_x;
    TextView textView_sum; // Переменная для доступа к компоненту со значением результата
    Button buttonSum; // Переменная для доступа к компоненту кнопки

    // Добавляем переменные для сохранения данных
    private static final String KEY_A = "a";
    private static final String KEY_B = "b";
    private static final String KEY_X = "x";
    private static final String KEY_RESULT = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Доступ к компонентам окна
        editText_a = findViewById(R.id.editText_a);
        editText_b = findViewById(R.id.editText_b);
        editText_x = findViewById(R.id.editText_x);
        textView_sum = findViewById(R.id.textView_sum);
        buttonSum = findViewById(R.id.buttonSum);

        checkDataAndToggleButton();

        editText_a.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkDataAndToggleButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        editText_b.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkDataAndToggleButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        editText_x.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkDataAndToggleButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // Восстанавливаем данные, если они доступны
        if (savedInstanceState != null) {
            editText_a.setText(savedInstanceState.getString(KEY_A));
            editText_b.setText(savedInstanceState.getString(KEY_B));
            editText_x.setText(savedInstanceState.getString(KEY_X));
            textView_sum.setText(savedInstanceState.getString(KEY_RESULT));
        }
    }

    // МЕТОД КНОПКИ РАСЧЕТА
    public void onClick(View v) {
        // Объявление локальных переменных
        double a, b, c, x;

        try { // НАЧАЛО ЗАЩИЩЕННОГО БЛОКА

            // Чтение данных из компонент
            a = Double.parseDouble(editText_a.getText().toString().trim());
            b = Double.parseDouble(editText_b.getText().toString().trim());
            x = Double.parseDouble(editText_x.getText().toString().trim());

            // Основной алгоритм
            if (x < 8) {
                c = a * a - 2 * x * x;
            } else {
                c = (a * a + 4 * x * x + b) / 2 / x;
            }

            // Вывод полученного значения в компонент
            textView_sum.setText(String.valueOf(c));

        } catch (Exception e) { // ЧТО ДЕЛАТЬ ЕСЛИ ВОЗНИКНЕТ ОШИБКА В БЛОКЕ МЕЖДУ "TRY" И "CATCH":

            // Вывод сообщения об ошибке
            textView_sum.setText("Неверные входные данные для расчета!");

        }  // КОНЕЦ ЗАЩИЩЕННОГО БЛОКА
    }

    private void checkDataAndToggleButton() {
        boolean dataEntered = !editText_a.getText().toString().isEmpty() &&
                !editText_b.getText().toString().isEmpty() &&
                !editText_x.getText().toString().isEmpty();

        buttonSum.setEnabled(dataEntered);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_A, editText_a.getText().toString());
        outState.putString(KEY_B, editText_b.getText().toString());
        outState.putString(KEY_X, editText_x.getText().toString());
        outState.putString(KEY_RESULT, textView_sum.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        editText_a.setText(savedInstanceState.getString(KEY_A));
        editText_b.setText(savedInstanceState.getString(KEY_B));
        editText_x.setText(savedInstanceState.getString(KEY_X));
        textView_sum.setText(savedInstanceState.getString(KEY_RESULT));
    }
}

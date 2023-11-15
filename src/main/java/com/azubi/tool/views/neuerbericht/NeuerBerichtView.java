package com.azubi.tool.views.neuerbericht;

import com.azubi.tool.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.PermitAll;

@PageTitle("Neuer Bericht")
@Route(value = "neuer-bericht", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class NeuerBerichtView extends HorizontalLayout {

    private TextField kalenderwocheField;
    private TextField lehrjahrField;
    private ComboBox<String> bundeslandComboBox;
    private TextField ausbildungsberufTextField;
    private ComboBox<String> taetigkeitComboBox;
    private ComboBox<String> wochenberichtComboBox;
    private TextArea beschreibungTextArea;
    private Button schreibButton;
    private TextArea ausgabeTextArea;

    public NeuerBerichtView() {
        initView();
        addFormFields();
        addListeners();
    }

    private void initView() {
        setClassName("NeuerBerichtView");

        ausbildungsberufTextField = new TextField();
        ausbildungsberufTextField.setWidth("380px");
        ausbildungsberufTextField.setPlaceholder("Ausbildungsberuf");

        kalenderwocheField = new TextField();
        kalenderwocheField.setPlaceholder("Kalenderwoche z.B. 03/2023");
        kalenderwocheField.setWidth("380px");

        lehrjahrField = new TextField();
        lehrjahrField.setPlaceholder("Lehrjahr z.B. 3");
        lehrjahrField.setWidth("380px");


        beschreibungTextArea = new TextArea();
        beschreibungTextArea.setWidth("380px");
        beschreibungTextArea.setHeight("300px");


        schreibButton = new Button("Schreib für mich");

        ausgabeTextArea = new TextArea();
        ausgabeTextArea.setWidth("380px");
        ausgabeTextArea.setHeight("300px");
        ausgabeTextArea.setVisible(false);


        bundeslandComboBox = new ComboBox<>();
        bundeslandComboBox.setPlaceholder("Bundesland");
        bundeslandComboBox.setWidth("380px");
        bundeslandComboBox.setAllowCustomValue(true);
        bundeslandComboBox.setAutoOpen(true);
        bundeslandComboBox.setAllowCustomValue(false);
        bundeslandComboBox.addValueChangeListener(event -> {
            String selectedValue = event.getValue();
            bundeslandComboBox.setPlaceholder(selectedValue);
        });

        wochenberichtComboBox = new ComboBox<>();
        wochenberichtComboBox.setPlaceholder("Form des Wochenberichtes");
        wochenberichtComboBox.setWidth("380px");
        wochenberichtComboBox.setAllowCustomValue(true);
        wochenberichtComboBox.setAutoOpen(true);
        wochenberichtComboBox.setAllowCustomValue(false);
                wochenberichtComboBox.addValueChangeListener(event -> {
            String selectedValue = event.getValue();
            wochenberichtComboBox.setPlaceholder(selectedValue);
        });

        taetigkeitComboBox = new ComboBox<>();
        taetigkeitComboBox.setPlaceholder("Wochentätigkeit");
        taetigkeitComboBox.setWidth("380px");
        taetigkeitComboBox.setAllowCustomValue(true);
        taetigkeitComboBox.setAutoOpen(true);
        taetigkeitComboBox.setAllowCustomValue(false);
        taetigkeitComboBox.addValueChangeListener(event -> {
            String selectedValue = event.getValue();
            taetigkeitComboBox.setPlaceholder(selectedValue);
        });


        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("500");
        layout.addClassName("right-align-textfield");
        layout.setAlignItems(Alignment.CENTER);
        layout.add(

                ausbildungsberufTextField,
                kalenderwocheField,
                lehrjahrField,
                taetigkeitComboBox,
                bundeslandComboBox,
                wochenberichtComboBox,
                new Label("Beschreibung:"),
                beschreibungTextArea,
                schreibButton,
                new Label("Ausgabe:"),
                ausgabeTextArea
        );

        add(layout);
    }
    private void addFormFields() {
        bundeslandComboBox.setItems("Baden-Württemberg", "Bayern", "Berlin", "Brandenburg", "Bremen", "Hamburg", "Hessen", "Mecklenburg-Vorpommern", "Niedersachsen", "Nordrhein-Westfalen", "Rheinland-Pfalz", "Saarland", "Sachsen", "Sachsen-Anhalt", "Schleswig-Holstein", "Thüringen");
        taetigkeitComboBox.setItems("Berufsschule", "Externe Schulung", "Interne Schulung", "Arbeit");
        wochenberichtComboBox.setItems("Stündlich", "Täglich", "Wöchentlich Stichpunkte", "Wöchentlich Fließtext");
    }

    private void addListeners() {
        schreibButton.addClickListener(event -> generateOutput());
    }



    private void generateOutput(){
        /*
        String prompt = "Schreibe einen Ausbildungs Wochenbericht in Deutsch Schreibe den Text Professionell erwähne im Bericht nicht das Bundesland das Lehrjahr oder den Ausbildungsberuf dies sind nur informationen für dich um einen fachlich Korrekten text zu generieren. "
                + " Kalenderwoche: " + kalenderwocheField.getValue()
                + " Lehrjahr: " + lehrjahrField.getValue()
                + " Bundesland: " + bundeslandComboBox.getValue()
                + " Ausbildungsberuf: " + ausbildungsberufTextField.getValue()
                + " Tätigkeit: " + taetigkeitComboBox.getValue()
                + " Art des Wochenberichtes: " + wochenberichtComboBox.getValue()
                + " Beschreibung: " + beschreibungTextArea.getValue();
        */

        String prompt = "Verfasse einen professionellen Wochenbericht im Rahmen deiner Ausbildung. Achte darauf, keine spezifischen Informationen wie das Bundesland (Bundesland), das Lehrjahr oder den Ausbildungsberuf zu erwähnen, da diese nur für eine fachlich korrekte Generierung des Textes dienen. Achte auch darauf, die gesetzlichen Feiertage in den Bundesländern sowie Landesfeiertage nicht mit einzubauen.\n\n"
                + "Die Kalenderwoche lautet: " + kalenderwocheField.getValue() + ".\n"
                + "Das Lehrjahr beträgt: " + lehrjahrField.getValue() + ".\n"
                + "Das Bundesland ist: " + bundeslandComboBox.getValue() + ".\n"
                + "Der Ausbildungsberuf lautet: " + ausbildungsberufTextField.getValue() + ".\n\n"
                + "Dein Wochenbericht für die Zeit während " + taetigkeitComboBox.getValue() + " erstellt werden. Gib bitte die entsprechende Tätigkeit an.\n\n"
                + "Zudem soll dein Wochenbericht in Form von: " + wochenberichtComboBox.getValue() + " verfasst werden.\n\n"
                + "Beschreibe deine Tätigkeiten und Fortbildungsbereiche angemessen für deinen Beruf anhand folgender Berichte aus der Woche: " + beschreibungTextArea.getValue() + ". Sollte der Bericht zu kurz werden, füge deiner Beschreibung bitte noch einige Punkte hinzu, die deine Tätigkeiten als " + ausbildungsberufTextField.getValue() + " im " + lehrjahrField.getValue() + ". Lehrjahr widerspiegeln.";

        System.out.println("Prompt: " + prompt);


        System.out.println("Prompt: "+ prompt);

        String response = SendPrompt.send(prompt);
        ausgabeTextArea.setValue(response);
        ausgabeTextArea.setVisible(true);
    }
    private HorizontalLayout createFormField(String label, TextField field) {
        HorizontalLayout container = new HorizontalLayout();
        container.add(new Label(label), field);
        return container;
    }

    private HorizontalLayout createFormField(String label, ComboBox<String> comboBox) {
        HorizontalLayout container = new HorizontalLayout();
        container.add(new Label(label), comboBox);
        return container;
    }


}

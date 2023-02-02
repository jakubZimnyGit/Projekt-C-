using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace klasy.nauka.cs
{
    internal class Program
    {
        struct Osoba
        {
            public string imie;
            public string nazwisko;
            public int dzien;
            public int miesiac;
            public int rok;
        }
        static string sciezka = ".\\Szkoła";
        static int minRok = 1998;               //Przykładowy zakres roczników w jakich muszą znajdować się uczniowie
        static int maxRok = 2008;
        static void Main(string[] args)
        {
            StworzFolderDocelowy(sciezka);

            Osoba[] klasa = new Osoba[0];       //klasa musi być tablicą o ilości elementów 0, ponieważ może wystąpić przypadek, w którym użytkownik jako pierwszą opcje wybierze 2.
            while (true)                        //W takim wypadku program zapisze pustą klasę.
            {
                int wybor = MenuGlowne();
                switch (wybor)
                {
                    case 1:
                        klasa = UtworzNowaKlase();
                        break;
                    case 2:
                        ZapiszKlaseDoPliku(klasa);
                        break;
                    case 3:
                        OdczytajKlaseZPliku();
                        break;
                    case 4:
                        WyszukajUczniaWKlasie();
                        break;
                    case 5:
                        return;

                }
            }
        }

        private static void WyszukajUczniaWKlasie()
        {
            string nazwaKlasy = PobierzString("Podaj nazwę klasy w której chcesz znaleźć ucznia: ");
            StreamReader sr;
            try
            {
                sr = new StreamReader(sciezka + "\\" + nazwaKlasy + ".csv");
            }
            catch (Exception)
            {
                Console.Clear();
                Console.WriteLine($"Nie ma klasy o nazwie: \"{nazwaKlasy}\" \n");
                return;
            }
            string subString = PobierzString("Podaj nazwisko lub część nazwiska ucznia: "); //Pobiera nazwisko bądź część nazwiska od użytkownika
            Console.Clear();
            string linia;
            int licznik = 0;
            int nr = 1;
            while ((linia = sr.ReadLine()) != null)     //Dopóki nie skończy się tekst, pętla będzie się powtarzać
            {
                string[] tab = linia.Split(' ');        //Przy każdym powtórzeniu pętli tworzymy nową tablicę string'ów, która zawiera kolejne ciągi znaków, czyli imie nazwisko, datę, jako elementy
                if (tab[1].ToLower().Contains(subString.ToLower())) // potrzebujemy drugi element, (tab[1] = nazwisko) aby porównać go z nazwiskiem podanym przez użytkownika.
                {
                    Console.WriteLine($"{nr}. {linia}");        //Jeśli warunek jest spełniony dla ucznia, program wyświetla wszystkie jego informacje na ekranie konsoli.
                    licznik++;
                    nr++;
                }
            }
            sr.Close();
            Console.WriteLine($"\nLiczba wyników wyszukiwania: {licznik} ");
            PowrotDoMenu();
        }

        private static void OdczytajKlaseZPliku()
        {
            Console.Clear();
            string nazwaKlasy = PobierzString("Podaj nazwę klasy której dane zostaną wyświetlone: ");
            StreamReader sr;
            try
            {
                sr = new StreamReader(sciezka + "\\" + nazwaKlasy + ".csv");
            }
            catch (Exception)
            {
                Console.Clear();
                Console.WriteLine($"Nie ma klasy o nazwie: \"{nazwaKlasy}\" \n");
                return;
            }
            string linia;
            int nr = 1;
            Console.WriteLine();
            while ((linia = sr.ReadLine()) != null)
            {
                Console.WriteLine($"{nr}. " + linia);
                Console.WriteLine();
                nr += 1;
            }
            PowrotDoMenu();
        }

        private static void ZapiszKlaseDoPliku(Osoba[] klasa)
        {
            string nazwaKlasy = PobierzString("Podaj nazwę klasy: ");       //nazwaKlasy= (np)3B        sciezka = ".\\szkoła"
            string SciezkaPliku = sciezka + "\\" + nazwaKlasy + ".csv";     //SciezkaPliku = ".\\szkoła\\3B.csv" 
            StreamWriter sw;
            try
            {
                sw = new StreamWriter(SciezkaPliku);
            }
            catch (Exception)
            {
                Console.WriteLine("wystąpił błąd");
                return;
            }
            for (int i = 0; i < klasa.Length; i++)
            {
                string linia = string.Format("{0}, {1}, {2}", klasa[i].imie, klasa[i].nazwisko, klasa[i].dzien + "." + klasa[i].miesiac + "." + klasa[i].rok);
                sw.WriteLine(linia);        //Zapisuje w podanym formacie każdego ucznia po kolei do komórek excela 
            }
            sw.Close();         //Zamyka strumień
            Console.Clear();
            Console.WriteLine($"Zapisano klasę do pliku: {nazwaKlasy}.csv\n");
        }

        private static void StworzFolderDocelowy(string sciezka)    //Funkcja tworzy folder do którego zapisywane będą klasy (w przypadku gdy takowy nie istnieje)
        {
            DirectoryInfo di;
            try
            {
                di = new DirectoryInfo(sciezka);
            }
            catch (Exception)
            {
                return;
            }
            if (!di.Exists)
            {
                di.Create();
            }
        }

        private static Osoba[] UtworzNowaKlase()
        {
            Console.Clear();
            int iloscUczniow = PobierzInt(1, 30, "Podaj ilość uczniów (max. 30)");   // Przy założeniu że w klasie może być max. 30 uczniów
            Osoba[] klasa = new Osoba[iloscUczniow];        //tworze tabele o ilosci elementow równej ilosci uczniow
            Console.Clear();
            for (int i = 0; i < iloscUczniow; i++)      // dodaje każdego ucznia po kolei używając pętli
            {
                Console.WriteLine($"Uczeń nr {i + 1}:\n ");
                Osoba uczen = new Osoba();
                uczen.imie = PobierzString("Podaj imie ucznia");
                uczen.nazwisko = PobierzString("Podaj nazwisko ucznia");
                uczen.rok = PobierzInt(minRok, maxRok, "Podaj rok urodzenia ucznia (1998 - 2008)");
                uczen.miesiac = PobierzInt(1, 12, "Podaj miesiąc urodzenia ucznia");
                switch (uczen.miesiac)                                          //Zależnie od miesiąca, zmienia się zakres dni które możemy podać, np. Kwiecień ma 30 dni, 
                {                                                               //więc w nie możemy podać 31 tak jak w przypadku np. stycznia.
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        uczen.dzien = PobierzInt(1, 31, "Podaj dzień urodzenia ucznia");
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        uczen.dzien = PobierzInt(1, 30, "Podaj dzień urodzenia ucznia");
                        break;
                    case 2:
                        uczen.dzien = PobierzInt(1, 28, "Podaj dzień urodzenia ucznia");
                        break;
                }
                klasa[i] = uczen;           //Dodajem stworzonego ucznia z przypisanymi zmiennymi do tablicy o nazwie klasa.
                Console.Clear();
            }
            Console.Clear();
            Console.WriteLine("Utworzono nową klasę\n");
            return klasa;           //zwraca tablice elementów typu "Osoba".
        }

        static string PobierzString(string komunikat)   //funkcja pomocnicza
        {
            Console.WriteLine(komunikat);
            string odp = Console.ReadLine();
            return odp;
        }

        static int MenuGlowne()     //funkcja służy do wyświetlenia menu głównego aplikacji, wybrany przez używkownika nr przypisany do konkretnej opcji 
        {
            Console.WriteLine("1. Utwórz nową klasę\n");
            Console.WriteLine("2. Zapisz klasę do pliku\n");
            Console.WriteLine("3. Odczytaj klasę z pliku\n");
            Console.WriteLine("4. Wyszukaj ucznia w klasie\n");
            Console.WriteLine("5. Zakończ program\n");
            int wybor = PobierzInt(1, 5, "Wybierz jedną z podanych opcji");
            return wybor;
        }

        private static int PobierzInt(int zakres1, int zakres2, string komunikat)       //funkcja pomocnicza
        {
            Console.WriteLine(komunikat);
            int nr = 'x';
            while (!int.TryParse(Console.ReadLine(), out nr) || nr < zakres1 || nr > zakres2)
            {
                Console.WriteLine($"Proszę spróbować ponownie, mając na uwadze poprawny zakres danych");
            }
            return nr;
        }
        static void PowrotDoMenu()
        {
            Console.WriteLine("\nAby wrócić do menu głównego proszę wcisnąć \"Enter\"");
            Console.ReadLine();
            Console.Clear();
            return;
        }
    }
}
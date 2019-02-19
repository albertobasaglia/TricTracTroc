# Tris in Android

![home](https://i.imgur.com/IYf4PW7.jpg)

## Studio
Il seguente progetto è una versione del TicTacToe in android studio.

Ho aggiunto alla mia versione la possiblità di connettersi tra due telefoni tramite Socket.

È comunque possibile giocare contro il computer e giocare da soli.

## Parte Online

I due programmi usano i Socket per connettersi

### Problemi incontrati

- Tutte le operazioni riguardanti il Networking NON possono essere effettuate sul Thread principale

- Gli elementi dell' interfaccia grafica possono essere modificati solo dal Thread principale dell'activity

Per risolvere questi due problemi ho usato un Handler.

Un handler permette di inviare un Messaggio al Thread che gestisce l'interfaccia grafica.

Per fare questo bisogna creare un estensione di Handler andando a fare l'override del metodo handleMessage.

In questo metodo andiamo a leggere il messaggio che ci è stato inviato e con uno switch decidiamo cosa fare.

Nel mio caso esiste solo un messaggio, ovvero un int che contiene la posizione della mossa effettuata dal giocatore remoto.

Per inviare un messaggio invece creo un Runnable che si occupa solamente di inviare il messaggio.

Se uno dei due utenti esce dall'activity viene inviato un -1, che fa capire al client remoto che uno dei due utenti si è disconnesso.

## Parte Offline

Nella parte offline il giocatore reale e l'intelligenza si alternano nelle mosse.

Il giocatore reale e la cpu si alternano la X e il O e la prima mossa.

Ho cercato di rispettare il pattern MVC realizzando una classe Game che contiene tutti i metodi relativi al gioco del Tris.

Viene creata un istanza della classe Game nell'activity che si occupa del gioco in modo da tenere separata la parte dell'interfaccia grafica da quella che gestisce le mosse e la rappresentazione nella memoria del gioco del tris.

![img1](https://i.imgur.com/BuDtrhB.jpg)
![img2](https://i.imgur.com/zL5pL1J.jpg)
#ifndef FIGURA_H
#define FIGURA_H

typedef enum {
    TRIANGLE, SQUARE, CIRCLE
} Typ;

typedef struct {
    Typ typfig;
    float x, y;
    float size; // kolo - promien , kwadrat/trojkat - dlugosc boku (zakladamy trojkat rownoboczny) //
} Figura;

Figura* nowa_figura(Typ typfig, float x, float y, float size);

float pole(Figura *f);
void przesun(Figura *f, float x, float y);
void show(Figura *f);
float sumapol(Figura *f[], int size);

#endif
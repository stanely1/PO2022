#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "figura.h"

Figura* nowa_figura(Typ typfig, float x, float y, float size)
{
    if(size < 0) printf("Zly rozmiar figury: %f\n", size), exit(1);

    Figura *f = malloc(sizeof(Figura));
    f->typfig = typfig;
    f->x = x;
    f->y = y;
    f->size = size;
    return f;
}

float pole(Figura *f)
{
    switch(f->typfig)
    {
        case SQUARE: return f->size * f->size; // S = a^2
        case TRIANGLE: return f->size * f->size * (float)sqrt(3.0) / (float)4.0; // S = a^2 * sqrt(3) / 4
        case CIRCLE: return (float)M_PI * f->size * f->size; // S = PI * r^2
    }
    return -1; // w przypadku bledu
}
void przesun(Figura *f, float x, float y)
{
    f->x += x;
    f->y += y;
}

char nazwa_typu[3][10] = {[TRIANGLE] = "trojkat", [SQUARE] = "kwadrat", [CIRCLE] = "kolo"};

void show(Figura *f)
{
    printf("TYP: %s, x: %f, y: %f, rozmiar: %f\n", nazwa_typu[f->typfig], f->x, f->y, f->size);
}
float sumapol(Figura *f[], int size)
{
    float r = 0;
    for(int i = 0; i < size; i++) r += pole(f[i]);
    return r;
}
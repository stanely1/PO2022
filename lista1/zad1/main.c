#include <stdio.h>
#include "figura.h"

int main()
{
    Figura *f = nowa_figura(SQUARE,1.0,-1.0,3.0);
    show(f);
    printf("%f\n", pole(f));
    przesun(f,1,2);
    show(f);

    Figura *g = nowa_figura(TRIANGLE,1.23,0.9,2.0);
    show(g);
    przesun(g,-1.23,-0.9);
    printf("%f\n", pole(g));
    show(g);

    Figura *h = nowa_figura(CIRCLE,-5.0,-9.0,1.0);
    show(h);
    przesun(h,3.0,-1.0);
    printf("%f\n", pole(h));
    show(h);

    Figura *t[3] = {f,g,h};
    printf("%lf\n", sumapol(t,3));

    Figura *i = nowa_figura(CIRCLE,1,1,-1);
}
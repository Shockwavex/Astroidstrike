package com.leonidgrinberg.framework;

import com.leonidgrinberg.framework.Graphics.PixmapFormat;

public interface Pixmap 
{
    int getWidth();
    int getHeight();
    PixmapFormat getFormat();
    void dispose();
}


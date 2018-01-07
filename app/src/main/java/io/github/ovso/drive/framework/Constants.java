package io.github.ovso.drive.framework;

import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;

/**
 * Created by jaeho on 2017. 10. 18
 */

public class Constants {
  public final static String CAULY_APP_CODE = "AZYsWa4X";
  public final static int DURATION_RECYCLERVIEW_ANI = 500;
  public final static int DELAY = 500;

  public static Notices getNotices() {
    final Notices notices = new Notices();

    notices.addNotice(new Notice("Dagger",
        "https://github.com/google/dagger",
        "Copyright 2012 The Dagger Authors",
        new ApacheSoftwareLicense20()));

    notices.addNotice(new Notice("RxAndroid",
        "https://github.com/ReactiveX/RxAndroid",
        "Copyright 2015 The RxAndroid authors",
        new ApacheSoftwareLicense20()));

    notices.addNotice(new Notice("RxBinding",
        "https://github.com/JakeWharton/RxBinding",
        "Copyright (C) 2015 Jake Wharton",
        new ApacheSoftwareLicense20()));

    notices.addNotice(new Notice("Timber",
        "https://github.com/JakeWharton/timber",
        "Copyright 2013 Jake Wharton",
        new ApacheSoftwareLicense20()));

    notices.addNotice(new Notice("Lombok Project",
        "https://projectlombok.org/setup/android",
        "Copyright © 2009-2017 The Project Lombok Authors, licensed under the MIT license.",
        new ApacheSoftwareLicense20()));

    notices.addNotice(new Notice("LeakCanary",
        "https://github.com/square/leakcanary",
        "Copyright 2015 Square, Inc.",
        new ApacheSoftwareLicense20()));

    notices.addNotice(new Notice("ReactiveLocation library for Android",
        "https://github.com/mcharmas/Android-ReactiveLocation",
        "Copyright (C) 2015 Michał Charmas (http://blog.charmas.pl)",
        new ApacheSoftwareLicense20()));

    notices.addNotice(new Notice("RxPermissions", "https://github.com/tbruyelle/RxPermissions",
        "Copyright (C) 2015 Thomas Bruyelle", new ApacheSoftwareLicense20()));

    notices.addNotice(new Notice("ButterKnipe", "https://github.com/JakeWharton/butterknife",
        "Copyright 2013 Jake Wharton", new ApacheSoftwareLicense20()));

    notices.addNotice(new Notice("Lombok", "https://github.com/rzwitserloot/lombok",
        "Copyright (C) 2009-2015 The Project Lombok Authors.\n"
            + "\n"
            + "Permission is hereby granted, free of charge, to any person obtaining a copy\n"
            + "of this software and associated documentation files (the \"Software\"), to deal\n"
            + "in the Software without restriction, including without limitation the rights\n"
            + "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n"
            + "copies of the Software, and to permit persons to whom the Software is\n"
            + "furnished to do so, subject to the following conditions:\n"
            + "\n"
            + "The above copyright notice and this permission notice shall be included in\n"
            + "all copies or substantial portions of the Software.\n"
            + "\n"
            + "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n"
            + "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n"
            + "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n"
            + "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n"
            + "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n"
            + "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN\n"
            + "THE SOFTWARE.\n", null));

    notices.addNotice(new Notice("RxJava", "https://github.com/ReactiveX/RxJava",
        "Copyright (c) 2016-present, RxJava Contributors.", new ApacheSoftwareLicense20()));

    notices.addNotice(
        new Notice("Retrofit", "https://github.com/square/retrofit", "Copyright 2013 Square, Inc.",
            new ApacheSoftwareLicense20()));

    notices.addNotice(new Notice("Okhttp", "https://github.com/square/okhttp",
        "Licensed under the Apache License, Version 2.0 (the \"License\")",
        new ApacheSoftwareLicense20()));

    return notices;
  }
}

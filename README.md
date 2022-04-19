# grek

Краткое описание что, где и как собирать

src/ru/mironova/grek - лежат исходники джавы
dst/grek.jar - собраный проект из джавы. Принимает аргументы аналогично grep.
    grek [OPTION]... PATTRON [File]...
    Pattern selection and interpretation:
        -r                  - recurse
        -n                  - print line number with output lines
        -i                  - ignore case
        --exclude=[PATTRON] - exclude files by name
    
srcK/ru/mironova/grek - лежат исходники котлина
dstK/grek.jar - собраный проект из котлина. Принимает аргументы аналогично grep.
    grek [OPTION]... PATTRON [File]...
    Pattern selection and interpretation:
        -r                  - recurse
        -n                  - print line number with output lines
        -i                  - ignore case
        --exclude=[PATTRON] - exclude files by name
    
Компиляция:
    java -jar grek.jar [OPTION]... PATTRON [File]...
    Советую предварительно првоерить есть ли на устройстве jdk (java -version), без джавы не компилятся оба варианта.
    
term/grek.deb - файл установки для Linux. Команда для установки sudo dpkg -i grek.deb
P.S. sudo - выполнение от имено администратора. Если это устройство не ваше, то без вариантов.
Возможные ошибки:
    1. Отсутствие Джавы. Да она нужна и для котлина.
    2. Если после установки grek не стала глобальной командой, то знайте установка ведётся в /usr/bin/ возможно в вашей системе, возможно эта директория не входит
в $PATH. Чтобы проверить вы можите написать echo ${PATH}. Чтобы добавить /ust/bin в PATH используется команда export. Настоятельно рекомендую ознокомится с документацией.
Если нет возможности для добавления в PATH, то вы можете пересобрать. Кратко об этом http://devhead.ru/read/ruchnaya-sborka-deb-paketa
    

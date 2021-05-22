#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <errno.h>
#include <ctype.h>
#include <unistd.h>
#include <time.h>
#include <signal.h>
#include <stdbool.h>

char* FILE_NAME = "data.txt";
char* TEMP_FILE = "temp.txt";

#define HARCRA_FEL_BUS1 SIGUSR1
#define HARCRA_FEL_BUS2 SIGUSR2

#define BUS1_KESZ SIGUSR1
#define BUS2_KESZ SIGUSR2

typedef struct patient {
    char name[20];
    unsigned int birth_year; 
    char phone[10];
    char accept_fee[5];
} patient;

int LENGTH = 50;

void print_menu() {
    printf("%s", "\nVakcin-áció - Regisztráció\n");
    printf("%s", "---------------------------\n");
    printf("%s", "1: Adat felvétel\n");
    printf("%s", "2: Adat módosítás\n");
    printf("%s", "3: Adat törlése\n");
    printf("%s", "4: Teljes lista készítése\n");
    printf("%s", "5: Adatok megjelenítése\n");
    printf("%s", "6: Napi munka indítása\n");
    printf("%s", "7: Kilépés\n");
    printf("\n");
    printf("%s", "Válasszon a menüből a kívánt művelet sorszámának megadásával.");
    printf("\n");
}

void show_list() {
    FILE * f = fopen(FILE_NAME, "r");

    if (f == NULL) {
        perror("File opening error\n"); 
        exit(1);
    }


    int index = 0;
    char* line = NULL;
    size_t len = 0;
    ssize_t read;

    printf("\nRegisztrált paciensek:\n");
    while ((read = getline(&line, &len, f)) != -1) {
        index++;
        printf("%d. %s", index, line);
    }
    printf("\n");

    fclose(f);
}

void add_data() {
    char name[20];
    char birth_year[5];
    char phone[12];
    char accept_fee[5];

    FILE * f = fopen(FILE_NAME, "r+");

    if (f == NULL) {
        perror("File opening error\n"); 
        exit(1);
    }

    printf("%s", "\nKérem adja meg az alábbi adatokat!\n");
    printf("%s", "Név: ");
    scanf("%s", name);
    printf("%s", "Születési év: ");
    scanf("%s", birth_year);
    printf("%s", "Telefonszám: ");
    scanf("%s", phone);
    printf("%s", "Fizetős felár vállalása (igen/nem): ");
    scanf("%s", accept_fee);

    fseek(f, 0, SEEK_END);
    fprintf(f, "%s %s %s %s\n", name, birth_year, phone, accept_fee);

    printf("%s", "\nSikeres adatfelvétel!\n");

    fclose(f);
}

void modify_data() {
    int index;
    char name[20];
    char birth_year[5];
    char phone[12];
    char accept_fee[5];

    FILE * f1 = fopen(FILE_NAME, "r");
    FILE * f2 = fopen(TEMP_FILE, "w");

    if (f1 == NULL || f2 == NULL) {
        perror("File opening error\n"); 
        exit(1);
    }

    printf("\nAz adatok módosításához adja meg a módosítani kívánt sor számát: \n");
    scanf("%d", &index);

    printf("\nAdja meg az új adatokat: \n");
    printf("\n%s", "Név: ");
    scanf("%s", name);
    printf("%s", "Születési év: ");
    scanf("%s", birth_year);
    printf("%s", "Telefonszám: ");
    scanf("%s", phone);
    printf("%s", "Fizetős felár vállalása (igen/nem): ");
    scanf("%s", accept_fee);


    int count = 0;
    char line[LENGTH];
    while ((fgets(line, LENGTH, f1)) != NULL) {
        count++;

        if (count == index) {
            fprintf(f2, "%s %s %s %s \n", name, birth_year, phone, accept_fee);
        } else {
            fputs(line, f2);
        }
    }

    if (count == 0) {
        printf("\nNem található ilyen adat.\n");
    } else {
        printf("%s", "\nSikeres adatmódosítás!\n");

        fclose(f1);
        fclose(f2);

        remove(FILE_NAME);
        rename(TEMP_FILE, FILE_NAME);
    }
}

void delete_data() {
    int index;

    FILE * f1 = fopen(FILE_NAME, "r");
    FILE * f2 = fopen(TEMP_FILE, "w");

    if (f1 == NULL || f2 == NULL) {
        perror("File opening error\n"); 
        exit(1);
    }

    printf("\nAz adatok törléséhez adja meg a törölni kívánt sor számát: \n");
    scanf("%d", &index);

    int count = 0;
    char line[50];
    while ((fgets(line, 50, f1)) != NULL) {
        count++;

        if (count == index) {
            fputs("", f2);
        } else {
            fputs(line, f2);
        }
    }

    if (count == 0) {
        printf("\nNem található ilyen adat.\n");
    } else {
         printf("%s", "\nSikeres törlés!\n");

        fclose(f1);
        fclose(f2);

        remove(FILE_NAME);
        rename(TEMP_FILE, FILE_NAME);
    }
}

void create_list() {
    int count;
    printf("%s", "\nAdja meg mennyi adatot kíván rögzíteni!\n");
    scanf("%d", &count);
    for (int i=0; i<count; ++i) {
        printf("\n%d %s", i+1, ". paciens rögzítése.\n");
        add_data();
    }
}

void get_patients(patient* patients, int count){
    FILE* f;
    f = fopen(FILE_NAME, "r");

    int patient_count = 0;
    ssize_t line_size;
    char* buf = NULL;
    size_t buf_size = 0;

    if (!f){
      perror("Hiba a file megnyitása közben!\n");
      exit(1);
    }

    line_size = getline(&buf, &buf_size, f);

    while (line_size >= 0 && patient_count < count) {
        if (strstr(buf, "OLTVA") == NULL){
            char* value = strtok(buf, " ");
            strcpy(patients[patient_count].name, value);
            value = strtok(NULL, " ");
            sscanf(value, " %u", &patients[patient_count].birth_year);
            value = strtok(NULL, " ");
            strcpy(patients[patient_count].phone, value);
            value = strtok(NULL, " ");
            strcpy(patients[patient_count].accept_fee, value);
            patient_count++;
        }
        line_size = getline(&buf, &buf_size, f);
    }
    free(buf);
    fclose(f);
}

int count_patients() {
    FILE* f;
    f = fopen(FILE_NAME, "r");
    int count = 0;
    ssize_t line_size;
    char* buf = NULL;
    size_t buf_size = 0;

    if(!f) {
      perror("Hiba a file megnyitása közben!\n");
      exit(1);
    }

    line_size = getline(&buf, &buf_size, f);

    while(line_size >= 0) {
        if (strstr(buf, "OLTVA") == NULL) {
            count++;
        }
        line_size = getline(&buf, &buf_size, f);
    }

    free(buf);
    fclose(f);
    return count;
}

void vaccinate(char *name) {
    FILE* f = fopen(FILE_NAME, "r");
    FILE* f2 = fopen(TEMP_FILE, "w");

    if (!f) {
        perror("File opening failed!");
        return;
    }

	char delimiter[] = " ";
    char lines[256][256];
    int index = 0;

    // find line by name
    while (fgets(lines[index], sizeof lines[index], f) != NULL) {
        char old[264];
        strcpy(old, lines[index]);
        int len = strlen(old);
        
        char* ptr = strtok(lines[index], delimiter);
        if (strcmp(ptr, name) != 0) {
            fputs(old, f2);
        } else {
            if (old[len-1]=='\n') {
                old[len-1]='\0';
            }
            // add "OLTVA" to end of line
            strcat(old," OLTVA\n");
            fputs(old, f2);
        }  

        while (ptr != NULL) {                
            ptr = strtok(NULL, delimiter);
        }
    }

    fclose(f);
    fclose(f2);
    remove(FILE_NAME);
    rename(TEMP_FILE, FILE_NAME);
}

void bus1(int* pipebus1, int count) {
    time_t t;
    srand((unsigned) time(&t));
    kill(getppid(), HARCRA_FEL_BUS1);

    printf("1-es busz készen áll az oltásra.\n");

    patient oltandok[count];
    read(pipebus1[0], oltandok, sizeof(patient) * count);

    printf("\n");
    int beoltott[count];
    for (int i=0; i<count; i++) {
        beoltott[i] = (rand() % 10 != 9);
        if (beoltott[i]) {
            printf("1-es busz fogadja: %s.\n", oltandok[i].name);
            printf("1-es busz beoltotta: %s.\n", oltandok[i].name);
        } else {
            printf("1-es busznál nem jelent meg: %s.\n", oltandok[i].name);
        }
    }
    write(pipebus1[1], beoltott, sizeof(int) * count);
    kill(getppid(), BUS1_KESZ);
    close(pipebus1[0]);
    close(pipebus1[1]);
}

void bus2(int* pipebus2, int count) {
    time_t t;
    srand((unsigned) time(&t));

    kill(getppid(), HARCRA_FEL_BUS2);

    printf("2-es busz készen áll az oltásra.\n");

    int capacity = count/2;

    patient oltandok[capacity];
    read(pipebus2[0], oltandok, sizeof(patient) * capacity);

    printf("\n");
    int beoltott[capacity];
    for (int i=0; i<capacity; i++){
        beoltott[i] = (rand() % 10 != 9);
        if (beoltott[i]) {
            printf("2-es busz fogadja: %s.\n", oltandok[i].name);
            printf("2-es busz beoltotta: %s.\n", oltandok[i].name);
        } else {
            printf("2-es busznál nem jelent meg: %s.\n", oltandok[i].name);
        }
    }

    write(pipebus2[1], beoltott, sizeof(int) * capacity);

    kill(getppid(), BUS2_KESZ);
    close(pipebus2[0]);
    close(pipebus2[1]);
}

void bus1_start(pid_t pid1, sigset_t sset, int sig, int* pipebus1, int count) {
    printf("1 oltóbusz indul.\n");
    sigwait(&sset, &sig);

    if (sig == HARCRA_FEL_BUS1){
		printf("1-es busz munkára kész.\n");
	}
    patient oltandok[count];
    get_patients(oltandok, count);

    printf("\n");
    for (int i = 0; i < count; i++) {
        printf("1-es busz várja: %s.\n", oltandok[i].name);
    }
    write(pipebus1[1], oltandok, sizeof(patient) * count);

    int beoltott[5];
    sigwait(&sset, &sig);
    if (sig == BUS1_KESZ) {
		printf("1-es busz végzett.\n");
	}
    read(pipebus1[0], beoltott, sizeof(int) * count);

    printf("\n");
    for (int i = 0; i < count; i++){
        if (beoltott[i]) {
            vaccinate(oltandok[i].name);
            printf("Oltás bejegyzése: %s.\n", oltandok[i].name);
        }
    }

    close(pipebus1[0]);
    close(pipebus1[1]);
    kill(pid1, SIGCHLD);
}

void bus2_start(pid_t pid1, pid_t pid2, sigset_t sset, int sig, int* pipebus1, int* pipebus2, int count){
    printf("2 oltóbusz indul.\n");
    patient oltandok[count];

    get_patients(oltandok, count);

    bool bus1_ready = false;
    bool bus2_ready = false;
    bool bus1_finished = false;
    bool bus2_finished = false;
    
    int capacity = count/2;
    while (!bus1_finished  || !bus2_finished) {    
        sigwait(&sset, &sig);
        if (sig == HARCRA_FEL_BUS1 || sig == BUS1_KESZ) {
            patient oltandok_1[capacity];
            for (int i=0; i<capacity; i++) {
                oltandok_1[i] = oltandok[i];
            }
	    	if (!bus1_ready){
                printf("1-es busz munkára kész.\n");
                bus1_ready = true;
                printf("\n");
                for (int i = 0; i < capacity; i++){
                    printf("1-es busz várja: %s.\n", oltandok[i].name);
                }
                write(pipebus1[1], oltandok, sizeof(patient) * capacity);
            }
            else if (bus1_ready && !bus1_finished) {
                printf("1-es busz végzett.\n");
                printf("\n");
                int beoltott[capacity];
                read(pipebus1[0], beoltott, sizeof(int) * capacity);
                for (int i=0; i<capacity; i++) {
                    if (beoltott[i]) {
                        vaccinate(oltandok[i].name);
                        printf("Oltás bejegyzése: %s.\n", oltandok[i].name);
                    }
                }
                printf("\n");
                bus1_finished = true;
            }
	    } else if (sig == HARCRA_FEL_BUS2 || sig == BUS2_KESZ) {
            patient oltandok_2[count-capacity];
            int index = 0;
            for (int i=capacity; i<count; i++) {
                oltandok_2[index] = oltandok[i];
                index++;
            }

	    	if (!bus2_ready){
                printf("2-es busz munkára kész\n");
                bus2_ready = true;
                printf("\n");
                for (int i=capacity; i<count; i++) {
                    printf("2-es busz várja: %s.\n", oltandok[i].name);
                }
                write(pipebus2[1], oltandok_2, sizeof(patient) * (count-capacity));
            } else if (bus2_ready && !bus2_finished) {
                printf("2-es busz végzett.\n");
                printf("\n");
                int beoltott[count-capacity];
                read(pipebus2[0], beoltott, sizeof(int) * (count-capacity));
                printf("\n");
                for (int i=0; i<count-capacity; i++) {
                    if (beoltott[i]) {
                        vaccinate(oltandok[capacity+i].name);
                        printf("Oltás bejegyzése: %s.\n", oltandok[capacity+i].name);
                    }
                }
                printf("\n");
                bus2_finished = true;
            }
	    }
    }

    close(pipebus1[0]);
    close(pipebus1[1]);
    close(pipebus2[0]);
    close(pipebus2[1]);
    kill(pid1, SIGCHLD);
    kill(pid2, SIGCHLD);
}

void start_process() {
    printf("process start\n");

    system("clear");
    sigset_t sset;
	sigemptyset(&sset);
	sigaddset(&sset, SIGUSR1);
	sigaddset(&sset, SIGUSR2);
	sigprocmask(SIG_BLOCK, &sset, NULL);
	int sig;
    
    int pipebus1[2];
    int pipebus2[2];
    pid_t pid_bus1;
    pid_t pid_bus2;

    int patient_count = count_patients();

    printf("Oltásra várók száma: %d.\n", patient_count);
    printf("\n");

    if (patient_count > 4) {

        if (pipe(pipebus1) == -1) {
            perror("1. pipe nyitásakor hiba történt!\n");
            exit(1);
        }

        pid_bus1 = fork();
        if (pid_bus1 > 0) {
            
            if (patient_count > 9) {
                // 2 busz indul
                printf("2 busz indul.\n");
                if (pipe(pipebus2) == -1) {
                    perror("2. pipe nyitásakor hiba történt!\n");
                    exit(1);
                }
                pid_bus2 = fork();
                if (pid_bus2 > 0){
                    bus2_start(pid_bus1, pid_bus2, sset, sig, pipebus1, pipebus2, patient_count);
                } else if (pid_bus2 == 0) {
                    bus2(pipebus2, patient_count);
                } else if (pid_bus2 < 0) {
                    perror("A 2-es buszt nem sikerült elindítani");
                    exit(1);
                }
            } else {
                // 1 busz indul
                printf("1 busz indul.\n");
                bus1_start(pid_bus1, sset, sig, pipebus1, patient_count);
            }

        } else if (pid_bus1 == 0) {
            int capacity = patient_count > 9 ? patient_count/2 : patient_count;
            bus1(pipebus1, capacity);
        } else if (pid_bus1 < 0) {
            perror("Az 1-es buszt nem sikerült elindítani");
        }
    } else {
        printf("Nincs elég oltandó személy.\n");
    }

    exit(0);
}


void start_registration() {
    int selection = 0;

    print_menu();

    scanf("%d", &selection);
    
    switch(selection) {
        case 1:
            add_data();
            break;
        case 2:
            modify_data();
            break;
        case 3:
            delete_data();
            break;
        case 4:
            create_list();
            break;
        case 5:
            show_list();
            break;
        case 6:
            start_process();
            break;
        case 7:
            exit(0);
        default:
            printf("Helytelen sorszám.\n");
            start_registration();
            break;
    }

    start_registration();
}


int main() {
    start_registration();

    /* start_process(); */

    return 0;
}

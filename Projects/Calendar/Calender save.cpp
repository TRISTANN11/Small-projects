#include <iostream>
#include <iomanip>

using namespace std;

void PrintHeader(int year, int month) {
	cout << "------------------" << endl;
	cout << "      " << month << " " << year << endl;
	cout << "------------------" << endl;
}

bool IsLeapYear(int year) {
	return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
}


int gettotalnumdaysinmonth(int year, int month) {
	if (month == 1) {
		return 31;
	}
	else if (month == 2) {
		if (isLeapYear(year)) {
			return 29;
		}
		else {
			return 28;
		}
	}
	else if (month == 3) {
		return 31;
	}
	else if (month == 4) {
		return 30;
	}
	else if (month == 5) {
		return 31;
	}
	else if (month == 6) {
		return 30;
	}
	else if (month == 7) {
		return 31;
	}
	else if (month == 8) {
		return 31;
	}
	else if (month == 9) {
		return 30;
	}
	else if (month == 10) {
		return 31;
	}
	else if (month == 11) {
		return 30;
	}
	else if (month == 12) {
		return 31;
	}

}

int gettotalnumdaysinyear(int year, int month) {
	int totaldays = 0;

	for (int i = 1800; i <= year; i++) {
		if (i < year) {
			for (int j = 1; j < 12; j++) {
				totaldays = totaldays + getotalnumdaysinmonth(i, j);
			}
		}if (i == year) {
			for (int j = 1; j < month; j++) {
				totaldays = totaldays + getottalnumdaysinmonth(i, j);
			}
		}
	}
}

int Getstartday(int year, int month) {
	int totaldays = gettotalnumdaysinyear(year, month);
	int start = (totaldays + 3) % 7;
	return start;
}

void printdays(int year, int month) {
	int start = Getstartday(year, month);
	int increment;
	if (start == 0) {
		increment = 0;
	}
	else if (start == 1) {
		increment = 1;
	}
	else if (start == 2) {
		increment = 2;
	}
	else if (start == 3) {
		increment = 3;
	}
	else if (start == 4) {
		increment = 4;
	}
	else if (start == 5) {
		increment == 5;
	}
	else if (start == 6) {
		increment = 6;
	}
	cout << "Sun Mon Tue Wed Thur Fri Sat" << endl;
	int in;
	int day = 1;
	while (in < increment) {
		in++;
		cout << "    ";
	}
	while (in < gettotalnumdaysinmonth(year, month) {
		if (in % 7 == 0) {
			cout << endl;
		}
		cout << " " << day << " ";
		day++;
	}
}



int main() {
	int year;
	int month;

	cout << "What year?" << endl;
	cin >> year;
	cout << "What month?" << endl;
	cin >> month;

	PrintHeader(year, month);
	printdays(year, month);

	return 0;
}
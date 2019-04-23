
#include <cstdio>
#include <cstdlib>
#include <iostream>
#include <sstream>
#include <vector>
#include <math.h>
#include <string>


using namespace std;





double travelingSalesman(vector<double> &cities){
return 0.0;




}




int main(){
	cout << "Please enter city values like this: (city number, city value) separated by spaces: ";
	string in;
	cin >> in;
	string split = strtok(in, " ,()");
	vector<string> cities;
	while(split != NULL){
		string city[2] = {atof(split.c_str()), atof(strtok(NULL, " ,()").cstr())};
		cities.push_back(city);
		split = strtok(NULL, " ,()");
	}
	
	for(int i = 0; i < cities.size(); i++){
		cout << "(" << cities[i][0] << ", " << cities[i][1] << ")\n";
	}

}

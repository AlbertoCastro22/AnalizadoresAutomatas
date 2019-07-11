#include<iostream>
#include<string.h>
using namespace std;
template<class t> class v;
typedef v<double> var;
template<class t> class v{
private:
	t valor;
	bool esLong;
public:
	v(t arg){
valor=arg;
esLong=(strcmp(typeid(arg).name(),"long")==0);
if(strcmp(typeid(arg).name(),"class v<double>")==0)
esLong=v<double>(arg).hasLongValue();
}
	void operator =(t arg){
v(arg);
}
	void operator ++(){
valor=valor+1;
}
	void operator --(){
valor=valor-1;
}
	friend void operator +=(var &acumulador,var operando);
	friend void operator -=(var &acumulador,var operando);
	friend void operator *=(var &acumulador,var operando);
	friend void operator /=(var &acumulador,var operando);
	friend ostream& operator <<(ostream& os,const var val);
	bool hasLongValue(){
return esLong;
}
	template<class t> friend t operator +(t operando, var val);
	template<class t> friend t operator -(t operando, var val);
	template<class t> friend t operator *(t operando, var val);
	template<class t> friend t operator /(t operando, var val);
	friend bool operator ==(var v1,var v2);
	friend bool operator !=(var v1,var v2);
	friend bool operator >(var v1,var v2);
	friend bool operator <(var v1,var v2);
	friend bool operator >=(var v1,var v2);
	friend bool operator <=(var v1,var v2);
};
ostream& operator <<(ostream& os, const var val){
os << val.valor;return os;
}
template<class t>t operator +(t operando, var val){
operando+=val.valor;
return operando;
}
template<class t>t operator -(t operando, var val){
operando-=val.valor;
return operando;
}
template<class t>t operator *(t operando, var val){
operando*=val.valor;
return operando;
}
template<class t>t operator /(t operando, var val){
operando/=val.valor;
return operando;
}
void operator +=(var &acumulador,var operando){
acumulador.valor=acumulador.valor+operando.valor;
}
void operator -=(var &acumulador,var operando){
acumulador.valor=acumulador.valor-operando.valor;
}
void operator *=(var &acumulador,var operando){
acumulador.valor=acumulador.valor*operando.valor;
}
void operator /=(var &acumulador,var operando){
if(acumulador.esLong)
acumulador.valor=long(acumulador.valor/operando.valor);
else 
acumulador.valor=acumulador.valor/operando.valor;
}
bool operator ==(var v1,var v2){
return (v1.valor==v2.valor);
}
bool operator !=(var v1,var v2){
return (v1.valor!=v2.valor);
}
bool operator <(var v1,var v2){
return (v1.valor<v2.valor);
}
bool operator >(var v1,var v2){
return (v1.valor>v2.valor);
}
bool operator >=(var v1,var v2){
return (v1.valor>=v2.valor);
}
bool operator <=(var v1,var v2){
return (v1.valor<=v2.valor);
}
//DENTRO DE ESTE MAIN ESTA EL CODIGO DE TRES DIRECCIONES
void main(){
var termino = 3;
var _l = termino;
var termino = 5 * _l;
var _d = termino;
}

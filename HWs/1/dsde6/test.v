`timescale 1ns / 1ps

module test;

	// Inputs
	reg [7:0] t;
	reg clk;
	reg rst;

	// Outputs
	wire cooler;
	wire heater;
	wire [3:0]rps;

	// Instantiate the Unit Under Test (UUT)
	incubator uut (
		.t(t), 
		.clk(clk), 
		.rst(rst), 
		.cooler(cooler), 
		.heater(heater), 
		.rps(rps)
	);

//T > 35    ->   cooler = 1
//T < 15 ->   heater = 1
//35> T > 30   ->   cooler = 0       heater = 0
//25>T > 15    ->   cooler = 0          heater = 0

	initial begin
		t = 22; //none
		clk = 0;
		rst = 1;
		//raise //heater
		#20
		rst =0;
		t = -9;
		#20
		t = -4;
		#20		
		t = 10;
		#20
		t = 23;
		#20
		t = 33;	//none	
		#20		
		t = 36;	//cooler	
		#20
		t = 42;		
		#20
		t = 55;	
		//fall		
		#20
		t = 39;
		#20
		t = 30;
		//#20
		//t = 22; //none

	end
      always clk = #10 ~clk;
endmodule


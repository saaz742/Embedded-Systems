`timescale 1ns / 1ps

module incubator(
input  signed [7:0]t,
input clk,
input rst,
output reg cooler , heater,
output reg [3:0]rps
    );

parameter q1 = 1;
parameter q2 = 2;
parameter q3 = 3;
   
parameter s0 = 0;
parameter s1 = 1;
parameter s2 = 2;
parameter s3 = 3;

reg [1:0]state, nstate;
reg [1:0]sstate, nsstate;


always @(posedge clk)begin
	if(rst) begin
		state <= q1;
		sstate <= s0;
		end
	else begin
		state <= nstate;
		sstate <= nsstate;
		end
end

//controller
always @* begin
	nstate <= state;
	nsstate <= sstate;
	
	case (state)
		q1: begin
			if(t > 35)begin
				nstate <= q2;
			end
			else if(t < 15) begin
				nstate <= q3;
			end
		end
		q2: begin
			if (t < 25)begin
				nstate <= q1;
			end
		end
		q3: begin
			if(t > 30)begin
				nstate <= q1;
			end
		end
	endcase
	
	case (sstate)
			s0 : begin
				if (t > 35) begin
					nsstate <= s1;
				end
			end
			s1 : begin
				if (t > 40) begin
					nsstate <= s2;
				end 
				else if (t < 25) begin
					nsstate <= s0;
				end
			end
			s2 : begin
				if (t > 45) begin
					nsstate <= s3;
				end 
				else if (t < 35) begin
					nsstate <= s1;
				end					
			end
			s3 : begin
				if (t< 40) begin
					nsstate <= s2;
				end			
			end											
	endcase
end


//datapath
always @* begin
	//nsstate <= sstate;
	case (sstate)
		s0: begin
			rps <= 0;	
		end
		s1: begin
			rps <= 4;
		end	
		s2: begin
			rps <= 6;
		end
		s3: begin
			rps <= 8;
		end
	endcase
	case (state)
		q1: begin
			heater = 0;
			cooler = 0;
		end
		q2: begin
			heater = 0;
			cooler = 1;
		end
		q3: begin
			heater = 1;
			cooler = 0;
		end
	endcase
end
endmodule

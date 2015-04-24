# jCron

Commands

#at: "date" => "command" identified "identifier". 
Calls a given command exaclty at the specified time! One time call.

 Example  at: 2015-05-04 14:15:00 => ls -alh indetified asdasd4545asd125as45

------------------------------------------------------------------------------------------------
#in: "verbal/common specification date" => "command" identified "identifier" 
calls command after specified time passed. One time call

 in: a minute, 5 minutes, 20 minutes              => command  indetified asdasd4545asd125as45
 
 in: an hour, an half of hour, 4 hours, 14 hours  => command  indetified asdasd4545asd125as45
 
 in: a week, 3 weeks, 7 weeks                     => command  indetified asdasd4545asd125as45
 
 in: a month, 4 months, 8 months                  => command  indetified asdasd4545asd125as45
 
 in: a year, 3 years , 15 years                   => command  indetified asdasd4545asd125as45
 
 common interval specification:  2Y3M4D7H2M1S
 
 Y - years
 M - months
 D - days
 H - hours
 M - minurs
 S - seconds

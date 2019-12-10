# FIWAPI

A Simple API to manipulate IPTABLES rules.

**MICROSERVICES:** It is possible use this API as a Microservice with a Eureka Service Discovery, to do that you just need to enable
the Service Discovery informing the value "true" to the ENABLE_SERVICE_DISCOVERY variable and what is the Service 
Discovery URL using the SERVICE_DISCOVERY_URL variable.

**NOTE:** The HTTP request methods, used in this projects, don't follow the HTTP specification. It was used the better way to
send the firewall rules informations. It would be quite complicated send the parameters throw the path on the 
GET HTTP request, instead it was used the POST method.

## Environment Variables
- FIWAPI_USER: User to access the API (Required: true),
- FIWAPI_PASSWORD: Password to access the API (Required: true)
- FIWAPI_PORT: Service port (Required: false, Default Value: 8891)
- FIWAPI_SERVICE_NAME: Service name (Required: false, Default Value: fiwapi)
- SERVICE_DISCOVERY_URL: address where the service discovery is running (Required: false, Default: http://localhost:8761/eureka/)
- ENABLE_SERVICE_DISCOVERY: Flag to enable or disable the service discovery (Required: false, Default Value: false)
- IP_INSTANCE: Address where the Fiwapi is running. This address will be used to others services

## Endpoints

### Parameters

- **table:** FILTER, NAT, MANGLE, RAW
- **chain:** Depends on the table (INPUT, FORWARD, OUTPUT, PREROUTING, POSTROUTING)
- **position:** Place where the rule will be added
- **inInterface:** Name of an interface via which a packet was received
- **outInterface:** Name of an interface via which a packet is going to be sent
- **protocol:** The protocol of the rule or of the packet to check. The specified protocol can be 
    one of tcp, udp, icmp, or all, or it can be a numeric value, representing one of these protocols 
    or a different one.
- **source:** Source specification.
- **destination:** Destination specification.
- **sourcePorts:** A source port or a list of source ports
- **destinationPorts:** A destination port or a list of destination ports
- **action:** This specifies the target of the rule
- **subparams:** A list of parameters used on some actions


#### Path: /api/v4/add/{table}/{chain}
**Method:** POST
<br/>
**Description:**
Include a rule to iptables.
<br/>
**Example:**
````
URL: /api/v4/add/nat/PREROUTING
Request Body (JSON):
{
	"protocol": "tcp",
	"source": "10.1.1.20",
	"destination": "10.1.1.5",
	"destinationPorts": "8080",
	"action": "DNAT",
	"subparams": [
        { 
            "name": "--to-destination", 
            "value": "10.5.1.3"
        }
    ]
}
````
<br/>


#### Path: /api/v4/add/{table}/{chain}/{position}
**Method:** POST
<br/>
**Description:**
Include a rule to iptables, but it is possible choose where the rule will be added.
<br/>
**Example:**
````
URL: /api/v4/add/filter/INPUT/2
Request Body (JSON):
{
	"protocol": "udp",
	"source": "10.1.1.30",
	"destination": "10.1.1.7",
	"destinationPorts": "5562,6255",
	"action": "ACCEPT"
}
````
<br/>


#### Path: /api/v4/check/{table}/{chain} 
**Method:** POST
<br/>
**Description:**
Check if a rule exists or no. If the response is "OK", the rule exists, on the other hand an error will be returned.
<br/>
**Example:**
````
URL: /api/v4/check/nat/PREROUTING
Request Body (JSON):
{
	"protocol": "tcp",
	"source": "10.1.1.20",
	"destination": "10.1.1.5",
	"destinationPorts": "8080",
	"action": "DNAT",
	"subparams": [
        { 
            "name": "--to-destination", 
            "value": "10.5.1.3"
        }
    ]
}
````


#### Path: /api/v4/delete/{table}/{chain}
**Method:** POST
<br/>
**Description:**
Remove a rule from iptables.
<br/>
**Example:**
````
URL: /api/v4/delete/filter/INPUT/2
Request Body (JSON):
{
	"protocol": "udp",
	"source": "10.1.1.30",
	"destination": "10.1.1.7",
	"destinationPorts": "5562,6255",
	"action": "ACCEPT"
}
````
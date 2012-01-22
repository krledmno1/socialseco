<%-- 
    Document   : usersList
    Created on : 18-Dec-2011, 17:22:30
    Author     : damian
--%>

<%@page import="socialseco.model.linkedin.*"%>
<%@page import="socialseco.dao.UserDAO"%>
<%@page import="java.util.List"%>
<%@page import="socialseco.linkedin.LinkedInAuthenticator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
Object obj = session.getAttribute("users");
List<LinkedinUser> users;
UserDAO dao = new UserDAO();
if(obj == null){
    dao.beginConversation();
    users = dao.readAllLinkedinUsersWithoutTransaction();
} else {
    users = (List<LinkedinUser>)obj;
}
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .user{
                padding: 0px 10px;
                margin: 10px;
                border: gray solid 1px;
            }
            .positions, .educations, .languages, .publications, .certifications,
            .skills, .patents {
                padding-bottom: 10px;
            }
            .education, .position, .language, .publication, .certification,
            .skill, .patent {
                margin: 0px 10px 10px 10px;
                padding: 2px;
                border: gray solid 1px;
            }
            .position { background-color: yellow; }
            .education { background-color: darkorange; }
            .language { background-color: violet; }
            .publication {background-color: greenyellow; }
            .certification {background-color: lightsalmon; }
            .skill {background-color: lightblue; }
            .patent {background-color: gold; }
            
        </style>
    </head>
    <body>
        <h1>LinkedIn Users in the Database</h1>
        
        <div id="nav">
            <a href="../">Back</a>
        </div>
        
        <div id="users">
        <% for(LinkedinUser user:users){ %>
            <div class="user">
                <h2><%= user.getName() %> <%= user.getSurname() == null ? "" : user.getSurname() %>
                    [ID: <%= user.getLinkedinId() %>; Linkedin ID: <%= user.getId() %>]</h2>
                <div>Id: <%= user.getId() %></div>
                <div>LinkedIn Id: <%= user.getLinkedinId() %></div>
                <div>Name: <%= user.getName() %></div>
                <div>Surname: <%= user.getSurname() == null ? "" : user.getSurname() %></div>
                <div>Headline: <%= user.getHeadline() == null ? "" : user.getHeadline() %></div>
                <div>Location: <%= user.getLocation() == null ? "" : user.getLocation() %></div>
                <div>Country Code: <%= user.getLocationCountryCode() == null ? "" : user.getLocationCountryCode() %></div>
                <div>Industry: <%= user.getIndustry() == null ? "" : user.getIndustry() %></div>
                <div>Summary: <%= user.getSummary() == null ? "" : user.getSummary() %></div>
                <div>Associations: <%= user.getAssociations() == null ? "" : user.getAssociations() %></div>
                <div>Honors: <%= user.getHonors() == null ? "" : user.getHonors() %></div>
                
                <h3>Educations</h3>
                <div class="educations">
                <% for(LinkedinEducation education:user.getEducations()){ %>
                    <div class="education">
                        School Name:
                        <span class="language_school_name"><%= education.getSchoolName() == null ? "" : education.getSchoolName() %></span>
                        <br/>
                        Field of Study:
                        <span class="language_field_of_study"><%= education.getFieldOfStudy() == null ? "" : education.getFieldOfStudy() %></span>
                        <br/>
                        Degree:
                        <span class="language_degree"><%= education.getDegree() == null ? "" : education.getDegree() %></span>
                        <br/>
                        Activities:
                        <span class="language_activities"><%= education.getActivities() == null ? "" : education.getActivities() %></span>
                        <br/>
                        Notes:
                        <span class="language_notes"><%= education.getNotes() == null ? "" : education.getNotes() %></span>
                        <br/>
                        Start Date:
                        <span class="language_start_date">
                            <%= education.getStartDate() == null ? "" : education.getStartDate().toString() %>
                        </span>
                        <br/>
                        End Date:
                        <span class="language_end_date">
                            <%= education.getEndDate() == null ? "" : education.getEndDate().toString() %>
                        </span>
                    </div>
                <% } %>
                </div>
                
                <h3>Positions</h3>
                <div class="positions">
                <% for(LinkedinPosition position:user.getPositions()){ %>
                    <div class="position">
                        Title:
                        <span class="position_title"><%= position.getTitle() == null ? "" : position.getTitle() %></span>
                        <br/>
                        Summary:
                        <span class="position_summary"><%= position.getSummary() == null ? "" : position.getSummary() %></span>
                        Is Current:
                        <span class="position_is_current"><%= position.getIsCurrent() == null ? "" : position.getIsCurrent() %></span>
                        <br/>
                        Start Date:
                        <span class="position_start_date">
                            <%= position.getStartDate() == null ? "" : position.getStartDate().toString() %>
                        </span>
                        <br/>
                        End Date:
                        <span class="position_end_date">
                            <%= position.getEndDate() == null ? "" : position.getEndDate().toString() %>
                        </span>
                        <br/>
                        Company Name:
                        <span class="position_company_name"><%= position.getCompany().getName() == null ? "" : position.getCompany().getName() %></span>
                        <br/>
                        Company Industry:
                        <span class="position_company_industry"><%= position.getCompany().getIndustry() == null ? "" : position.getCompany().getIndustry() %></span>
                        <br/>
                        Company Size:
                        <span class="position_company_size"><%= position.getCompany().getSize() == null ? "" : position.getCompany().getSize() %></span>
                        <br/>
                        Company Type:
                        <span class="position_company_type"><%= position.getCompany().getType() == null ? "" : position.getCompany().getType() %></span>
                        <br/>
                        Company Ticker:
                        <span class="position_company_tocker"><%= position.getCompany().getTicker() == null ? "" : position.getCompany().getTicker() %></span>
                    </div>
                <% } %>
                </div>
                
                <h3>Publications</h3>
                <div class="publications">
                <% for(LinkedinPublication publication:user.getPublications()){ %>
                    <div class="publication">
                        Title:
                        <span class="publication_title"><%= publication.getTitle() == null ? "" : publication.getTitle() %></span>
                        <br/>
                        Publisher:
                        <span class="publication_publisher"><%= publication.getPublisher() == null ? "" : publication.getPublisher() %></span>
                        <br/>
                        Date:
                        <span class="publication_date">
                            <%= publication.getDate() == null ? "" : publication.getDate().toString() %>
                        </span>
                        <br/>
                        URL:
                        <span class="publication_url"><%= publication.getUrl() == null ? "" : publication.getUrl() %></span>
                        <br/>
                        Summary:
                        <span class="publication_summary"><%= publication.getSummary() == null ? "" : publication.getSummary() %></span>
                    </div>
                <% } %>
                </div>
                
                <h3>Patents</h3>
                <div class="patents">
                <% for(LinkedinPatent patent:user.getPatents()){ %>
                    <div class="patent">
                        Title:
                        <span class="patent_title"><%= patent.getTitle() == null ? "" : patent.getTitle() %></span>
                        <br/>
                        Summary:
                        <span class="patent_summary"><%= patent.getSummary() == null ? "" : patent.getSummary() %></span>
                        <br/>
                        Number:
                        <span class="patent_number"><%= patent.getNumber() == null ? "" : patent.getNumber() %></span>
                        <br/>
                        Date:
                        <span class="patent_date">
                            <%= patent.getDate() == null ? "" : patent.getDate().toString() %>
                        </span>
                        <br/>
                        URL:
                        <span class="patent_url"><%= patent.getUrl() == null ? "" : patent.getUrl() %></span>
                    </div>
                <% } %>
                </div>
                
                <h3>Certificates</h3>
                <div class="certifications">
                <% for(LinkedinCertification certification:user.getCertifications()){ %>
                    <div class="certification">
                        Name:
                        <span class="certification_name"><%= certification.getName() == null ? "" : certification.getName() %></span>
                        <br/>
                        Number:
                        <span class="certification_number"><%= certification.getNumber() == null ? "" : certification.getNumber() %></span>
                        <br/>
                        Start Date:
                        <span class="certification_start_date">
                            <%= certification.getStartDate() == null ? "" : certification.getStartDate().toString() %>
                        </span>
                        <br/>
                        End Date:
                        <span class="certification_end_date">
                            <%= certification.getEndDate() == null ? "" : certification.getEndDate().toString() %>
                        </span>
                        <br/>
                    </div>
                <% } %>
                </div>
                
                <h3>Languages</h3>
                <div class="languages">
                <% for(LinkedinLanguage language:user.getLanguages()){ %>
                    <div class="language">
                        <div class="language_name"><%= language.getName() %></div>
                    </div>
                <% } %>
                </div>
                
                <h3>Skills</h3>
                <div class="skills">
                <% for(LinkedinSkill skill:user.getSkills()){ %>
                    <div class="skill">
                        <div class="skill_name"><%= skill.getName() %></div>
                    </div>
                <% } %>
                </div>
            </div>
        <% } %>
        </div>
    </body>
</html>

<%
if(obj == null){
    dao.endConversation();
}
%>

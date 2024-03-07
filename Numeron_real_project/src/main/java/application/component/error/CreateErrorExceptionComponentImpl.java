package application.component.error;

import org.springframework.stereotype.Component;

import application.component.message.MessageAccessor;

@Component
public class CreateErrorExceptionComponentImpl implements CreateErrorExceptionComponent {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Numer0nUncontinuableException createNumer0nUncontinuableException(String sFuncId, String className, String methodName, String expErrorCd, String messageCd, String jobId, String errMessage, Throwable e, String... fillChar) {
		String outMessage = MessageAccessor.getMessage(messageCd, fillChar);
		Numer0nInfoErrDTO errDTO = createNumer0nInfoErrDTO(sFuncId, className, methodName, expErrorCd, messageCd, jobId, outMessage, errMessage, e, fillChar);
		return new Numer0nUncontinuableException(errDTO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Numer0nInfoErrDTO createNumer0nInfoErrDTO(String sFuncId, String className,  String methodName, String expErrorCd, String messageCd, String jobId, String outMessage, String outDetailMessage, Throwable e, String[] fillChar) {
		Numer0nInfoErrDTO dto = new Numer0nInfoErrDTO();
		if (sFuncId != null) {
			dto.setSFuncId(sFuncId);
		}
		if (className != null) {
			dto.setClassName(className);
		}
		if (methodName != null) {
			dto.setMethodName(methodName);
		}
		if (expErrorCd != null) {
			dto.setExpErrorCd(expErrorCd);
		}
		if (messageCd != null) {
			dto.setMessageCd(messageCd);
		}
		if (jobId != null) {
			dto.setJobId(jobId);
		}
		if (outMessage != null) {
			dto.setOutMessage(outMessage);
		}
		if (outDetailMessage != null) {
			dto.setOutErrorDetailMessage(outDetailMessage);
		}
		if (e != null) {
			dto.setErrorClass(e);
		}
		return dto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Numer0nUncontinuableException createOnlyFuncIdAndFillCharOtherDTONumer0nUncontinuableException(String sFuncId, Numer0nInfoErrDTO dto, String... fillChar) {
		createNumer0nUncontinuableException(sFuncId, dto.getClassName(), dto.getMethodName(), dto.getExpErrorCd(), dto.getMessageCd(), dto.getJobId(), dto.getOutErrorDetailMessage(), dto.getErrorClass(), fillChar);
		return null;

	}

}
